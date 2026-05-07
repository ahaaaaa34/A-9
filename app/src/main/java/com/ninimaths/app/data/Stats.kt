package com.ninimaths.app.data

import android.content.Context
import org.json.JSONObject

data class Stats(
    val totalSolved: Int = 0,
    val points: Int = 0,
    val highScores: Map<String, Int> = mapOf("add" to 0, "subtract" to 0, "multiply" to 0, "divide" to 0, "mix" to 0),
    val bestTimes: Map<String, Float> = emptyMap()
) {
    fun level() = totalSolved / 100 + 1
}

private const val PREFS_NAME = "mathix_stats"

fun loadStats(context: Context): Stats {
    val p = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val hs = mutableMapOf("add" to 0, "subtract" to 0, "multiply" to 0, "divide" to 0, "mix" to 0)
    runCatching {
        val obj = JSONObject(p.getString("high_scores", "{}") ?: "{}")
        obj.keys().forEach { k -> hs[k] = obj.getInt(k) }
    }
    val bt = mutableMapOf<String, Float>()
    runCatching {
        val obj = JSONObject(p.getString("best_times", "{}") ?: "{}")
        obj.keys().forEach { k -> bt[k] = obj.getDouble(k).toFloat() }
    }
    return Stats(p.getInt("total_solved", 0), p.getInt("points", 0), hs, bt)
}

fun saveStats(context: Context, stats: Stats) {
    val hsObj = JSONObject()
    stats.highScores.forEach { (k, v) -> hsObj.put(k, v) }
    val btObj = JSONObject()
    stats.bestTimes.forEach { (k, v) -> btObj.put(k, v.toDouble()) }
    context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
        .putInt("total_solved", stats.totalSolved)
        .putInt("points", stats.points)
        .putString("high_scores", hsObj.toString())
        .putString("best_times", btObj.toString())
        .apply()
}

fun recordResult(context: Context, settings: QuizSettings, correct: Int, elapsedMs: Long) {
    val stats = loadStats(context)
    val opId = settings.operation.id
    val hs = stats.highScores.toMutableMap()
    if (correct > (hs[opId] ?: 0)) hs[opId] = correct
    val bt = stats.bestTimes.toMutableMap()
    if (correct > 0 && elapsedMs > 0) {
        val tpq = elapsedMs / 1000f / settings.questionCount
        val key = "${settings.firstDigits}_${settings.secondDigits}_$opId"
        if (!bt.containsKey(key) || tpq < bt[key]!!) bt[key] = tpq
    }
    saveStats(context, Stats(stats.totalSolved + correct, stats.points + correct, hs, bt))
}
