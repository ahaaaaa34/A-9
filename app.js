'use strict';

// ════════════════════════════════════════════
// WORD DATA
// ════════════════════════════════════════════
const WORDS = [
  // 英検3級
  { id:  0, en: 'species',     ja: '種',              lv: '3級',   lvCls: 'lv-3'    },
  { id:  1, en: 'extinct',     ja: '絶滅した',         lv: '3級',   lvCls: 'lv-3'    },
  { id:  2, en: 'disappear',   ja: '消える',           lv: '3級',   lvCls: 'lv-3'    },
  { id:  3, en: 'save',        ja: '救う',             lv: '3級',   lvCls: 'lv-3'    },
  { id:  4, en: 'reason',      ja: '理由',             lv: '3級',   lvCls: 'lv-3'    },
  { id:  5, en: 'harm',        ja: '害',               lv: '3級',   lvCls: 'lv-3'    },
  { id:  6, en: 'activity',    ja: '活動',             lv: '3級',   lvCls: 'lv-3'    },
  { id:  7, en: 'destruction', ja: '破壊',             lv: '3級',   lvCls: 'lv-3'    },
  { id:  8, en: 'hunting',     ja: '狩猟',             lv: '3級',   lvCls: 'lv-3'    },
  { id:  9, en: 'destroy',     ja: '破壊する',         lv: '3級',   lvCls: 'lv-3'    },
  { id: 10, en: 'natural',     ja: '自然の',           lv: '3級',   lvCls: 'lv-3'    },
  { id: 11, en: 'environment', ja: '環境',             lv: '3級',   lvCls: 'lv-3'    },
  { id: 12, en: 'pollution',   ja: '汚染',             lv: '3級',   lvCls: 'lv-3'    },
  { id: 13, en: 'factory',     ja: '工場',             lv: '3級',   lvCls: 'lv-3'    },
  { id: 14, en: 'contain',     ja: '含む',             lv: '3級',   lvCls: 'lv-3'    },
  { id: 15, en: 'area',        ja: '地域',             lv: '3級',   lvCls: 'lv-3'    },
  { id: 16, en: 'result',      ja: '結果',             lv: '3級',   lvCls: 'lv-3'    },
  { id: 17, en: 'price',       ja: '価格',             lv: '3級',   lvCls: 'lv-3'    },
  { id: 18, en: 'example',     ja: '例',               lv: '3級',   lvCls: 'lv-3'    },
  { id: 19, en: 'almost',      ja: 'ほとんど',         lv: '3級',   lvCls: 'lv-3'    },
  { id: 20, en: 'medicine',    ja: '薬',               lv: '3級',   lvCls: 'lv-3'    },
  { id: 21, en: 'creature',    ja: '生き物',           lv: '3級',   lvCls: 'lv-3'    },
  { id: 22, en: 'special',     ja: '特別な',           lv: '3級',   lvCls: 'lv-3'    },
  { id: 23, en: 'dish',        ja: '料理',             lv: '3級',   lvCls: 'lv-3'    },
  { id: 24, en: 'step',        ja: '手段',             lv: '3級',   lvCls: 'lv-3'    },
  { id: 25, en: 'protect',     ja: '守る',             lv: '3級',   lvCls: 'lv-3'    },
  { id: 26, en: 'refuse',      ja: '拒む',             lv: '3級',   lvCls: 'lv-3'    },
  { id: 27, en: 'product',     ja: '製品',             lv: '3級',   lvCls: 'lv-3'    },
  { id: 28, en: 'against',     ja: '〜に反して',       lv: '3級',   lvCls: 'lv-3'    },
  { id: 29, en: 'law',         ja: '法律',             lv: '3級',   lvCls: 'lv-3'    },
  { id: 30, en: 'provide',     ja: '提供する',         lv: '3級',   lvCls: 'lv-3'    },
  { id: 31, en: 'cooperate',   ja: '協力する',         lv: '3級',   lvCls: 'lv-3'    },
  { id: 32, en: 'planet',      ja: '地球',             lv: '3級',   lvCls: 'lv-3'    },
  { id: 33, en: 'enjoy',       ja: '楽しむ',           lv: '3級',   lvCls: 'lv-3'    },
  // 英検準2級
  { id: 34, en: 'endangered',  ja: '絶滅危惧の',       lv: '準2級', lvCls: 'lv-pre2' },
  { id: 35, en: 'extinction',  ja: '絶滅',             lv: '準2級', lvCls: 'lv-pre2' },
  { id: 36, en: 'habitat',     ja: '生息地',           lv: '準2級', lvCls: 'lv-pre2' },
  { id: 37, en: 'overfishing', ja: '乱獲',             lv: '準2級', lvCls: 'lv-pre2' },
  { id: 38, en: 'chemical',    ja: '化学物質',         lv: '準2級', lvCls: 'lv-pre2' },
  { id: 39, en: 'poison',      ja: '毒',               lv: '準2級', lvCls: 'lv-pre2' },
  { id: 40, en: 'fur',         ja: '毛皮',             lv: '準2級', lvCls: 'lv-pre2' },
  { id: 41, en: 'bone',        ja: '骨',               lv: '準2級', lvCls: 'lv-pre2' },
  { id: 42, en: 'skin',        ja: '皮',               lv: '準2級', lvCls: 'lv-pre2' },
  { id: 43, en: 'sport',       ja: 'スポーツ・娯楽',   lv: '準2級', lvCls: 'lv-pre2' },
  { id: 44, en: 'seal',        ja: 'アザラシ',         lv: '準2級', lvCls: 'lv-pre2' },
  { id: 45, en: 'shoot',       ja: '撃つ',             lv: '準2級', lvCls: 'lv-pre2' },
  { id: 46, en: 'whale',       ja: 'クジラ',           lv: '準2級', lvCls: 'lv-pre2' },
  { id: 47, en: 'tuna',        ja: 'マグロ',           lv: '準2級', lvCls: 'lv-pre2' },
  { id: 48, en: 'shark',       ja: 'サメ',             lv: '準2級', lvCls: 'lv-pre2' },
  { id: 49, en: 'individual',  ja: '個人',             lv: '準2級', lvCls: 'lv-pre2' },
  { id: 50, en: 'government',  ja: '政府',             lv: '準2級', lvCls: 'lv-pre2' },
  { id: 51, en: 'pollute',     ja: '汚染する',         lv: '準2級', lvCls: 'lv-pre2' },
  { id: 52, en: 'farmer',      ja: '農家',             lv: '準2級', lvCls: 'lv-pre2' },
  { id: 53, en: 'company',     ja: '会社',             lv: '準2級', lvCls: 'lv-pre2' },
  { id: 54, en: 'financial',   ja: '金銭的な',         lv: '準2級', lvCls: 'lv-pre2' },
  { id: 55, en: 'penalty',     ja: '罰',               lv: '準2級', lvCls: 'lv-pre2' },
  { id: 56, en: 'public',      ja: '一般市民',         lv: '準2級', lvCls: 'lv-pre2' },
  { id: 57, en: 'trade',       ja: '取引する',         lv: '準2級', lvCls: 'lv-pre2' },
  { id: 58, en: 'funding',     ja: '資金',             lv: '準2級', lvCls: 'lv-pre2' },
  { id: 59, en: 'sanctuary',   ja: '保護区',           lv: '準2級', lvCls: 'lv-pre2' },
  { id: 60, en: 'breed',       ja: '繁殖させる',       lv: '準2級', lvCls: 'lv-pre2' },
  { id: 61, en: 'release',     ja: '放つ',             lv: '準2級', lvCls: 'lv-pre2' },
  { id: 62, en: 'wild',        ja: '野生',             lv: '準2級', lvCls: 'lv-pre2' },
  // 英検2級
  { id: 63, en: 'oryx',        ja: 'オリックス（レイヨウの一種）', lv: '2級', lvCls: 'lv-2' },
  { id: 64, en: 'crocodile',   ja: 'ワニ',             lv: '2級',   lvCls: 'lv-2'    },
  // 英検準1級以上
  { id: 65, en: 'Arabian',     ja: 'アラビアの',       lv: '準1級+',lvCls: 'lv-pre1' },
];

// ════════════════════════════════════════════
// STATE
// ════════════════════════════════════════════
const S = {
  mode: 'flashcard',   // 'flashcard' | 'writing'
  dir:  'en-jp',       // 'en-jp' | 'jp-en'
  fc: {
    deck: [],          // word ids in current round
    idx:  0,
    flipped: false,
    knownIds: new Set(),
    roundUnknown: new Set(),
    roundYes: 0,
  },
  wr: {
    deck: [],
    idx:  0,
    consecutive: {},   // wordId → 0|1|2
    knownIds: new Set(),
    firstAttempt: true,
  },
};

// ════════════════════════════════════════════
// PERSISTENCE
// ════════════════════════════════════════════
function persist() {
  try {
    localStorage.setItem('vocab-v1', JSON.stringify({
      dir:   S.dir,
      mode:  S.mode,
      fcKnown: [...S.fc.knownIds],
      wrKnown: [...S.wr.knownIds],
      wrConsecutive: S.wr.consecutive,
    }));
  } catch (_) {}
}

function hydrate() {
  try {
    const raw = localStorage.getItem('vocab-v1');
    if (!raw) return;
    const d = JSON.parse(raw);
    if (d.dir)  S.dir  = d.dir;
    if (d.mode) S.mode = d.mode;
    if (d.fcKnown) d.fcKnown.forEach(id => S.fc.knownIds.add(id));
    if (d.wrKnown) d.wrKnown.forEach(id => S.wr.knownIds.add(id));
    if (d.wrConsecutive) S.wr.consecutive = d.wrConsecutive;
  } catch (_) {}
}

// ════════════════════════════════════════════
// UTILITIES
// ════════════════════════════════════════════
function shuffle(arr) {
  const a = [...arr];
  for (let i = a.length - 1; i > 0; i--) {
    const j = Math.random() * (i + 1) | 0;
    [a[i], a[j]] = [a[j], a[i]];
  }
  return a;
}

function $ (id) { return document.getElementById(id); }

function unknown(knownSet) {
  return WORDS.filter(w => !knownSet.has(w.id));
}

// ════════════════════════════════════════════
// SPEECH
// ════════════════════════════════════════════
let speakTimer = null;

function speak(text) {
  if (!window.speechSynthesis) return;
  window.speechSynthesis.cancel();
  clearTimeout(speakTimer);
  // small delay to work around mobile browser quirks
  speakTimer = setTimeout(() => {
    const u = new SpeechSynthesisUtterance(text);
    u.lang  = 'en-US';
    u.rate  = 0.88;
    u.pitch = 1;
    window.speechSynthesis.speak(u);
  }, 60);
}

// ════════════════════════════════════════════
// PROGRESS BAR
// ════════════════════════════════════════════
function setProgress(done, total) {
  $('progFill').style.width = total > 0 ? `${(done / total) * 100}%` : '0%';
  $('progText').textContent = `${done} / ${total}`;
}

// ════════════════════════════════════════════
// SECTION VISIBILITY
// ════════════════════════════════════════════
function showSection(id) {
  ['secFlashcard', 'secWriting', 'secRound', 'secDone'].forEach(s => {
    const el = $(s);
    if (!el) return;
    el.style.display = s === id ? (s === 'secFlashcard' || s === 'secWriting' ? 'flex' : 'flex') : 'none';
  });
}

// ════════════════════════════════════════════
// FLASHCARD MODE
// ════════════════════════════════════════════
function initFlashcard() {
  const fc = S.fc;
  const deck = unknown(fc.knownIds);
  if (deck.length === 0) { showAllDone('flashcard'); return; }

  fc.deck = shuffle(deck.map(w => w.id));
  fc.idx  = 0;
  fc.flipped = false;
  fc.roundUnknown = new Set();
  fc.roundYes = 0;

  showSection('secFlashcard');
  renderFC();
}

function setBadge(el, word) {
  el.textContent  = word.lv;
  el.className    = `lv-badge ${word.lvCls}`;
}

function renderFC() {
  const fc = S.fc;
  if (fc.idx >= fc.deck.length) { showRoundComplete(); return; }

  const word = WORDS[fc.deck[fc.idx]];
  const enJp = S.dir === 'en-jp';

  // Reset flip without animation
  const inner = $('fcInner');
  inner.style.transition = 'none';
  inner.classList.remove('flipped', 'swipe-r', 'swipe-l');
  void inner.offsetWidth; // force reflow
  inner.style.transition = '';

  fc.flipped = false;
  $('fcActions').style.visibility = 'hidden';

  const frontTxt = enJp ? word.en : word.ja;
  const backTxt  = enJp ? word.ja : word.en;

  $('fcFrontWord').textContent = frontTxt;
  $('fcBackWord').textContent  = backTxt;

  setBadge($('fcBadgeFront'), word);
  setBadge($('fcBadgeBack'),  word);

  // Show speak button only where English is visible
  $('fcSpkFront').style.display = enJp ? 'flex' : 'none';
  $('fcSpkBack').style.display  = enJp ? 'none' : 'flex';

  if (enJp) speak(word.en); // auto-play when English is on front

  setProgress(fc.idx, fc.deck.length);
  updateFCStats();
}

function flipFC() {
  if (S.fc.flipped) return;
  S.fc.flipped = true;
  $('fcInner').classList.add('flipped');
  $('fcActions').style.visibility = 'visible';
  if (S.dir === 'jp-en') {
    speak(WORDS[S.fc.deck[S.fc.idx]].en); // auto-play on flip
  }
}

function markFC(known) {
  const fc = S.fc;
  const wordId = fc.deck[fc.idx];
  if (known) {
    fc.knownIds.add(wordId);
    fc.roundYes++;
  } else {
    fc.roundUnknown.add(wordId);
  }

  const inner = $('fcInner');
  const cls   = known ? 'swipe-r' : 'swipe-l';
  inner.classList.add(cls);

  setTimeout(() => {
    fc.idx++;
    persist();
    renderFC();
  }, 270);
}

function showRoundComplete() {
  const fc = S.fc;
  const noCount = fc.roundUnknown.size;

  if (noCount === 0) { showAllDone('flashcard'); return; }

  showSection('secRound');
  $('roundStats').innerHTML =
    `✓ 知ってる: <strong>${fc.roundYes}</strong>語<br>` +
    `✗ 知らない: <strong>${noCount}</strong>語<br>` +
    `次のラウンドで <strong>${noCount}</strong>語に挑戦！`;

  $('roundContinue').onclick = () => {
    fc.deck = shuffle([...fc.roundUnknown]);
    fc.idx  = 0;
    fc.flipped = false;
    fc.roundUnknown = new Set();
    fc.roundYes = 0;
    showSection('secFlashcard');
    renderFC();
  };

  $('roundReset').onclick = () => {
    fc.knownIds = new Set();
    persist();
    initFlashcard();
  };
}

function updateFCStats() {
  $('fcStatYes').textContent = `✓ ${S.fc.roundYes}`;
  $('fcStatNo').textContent  = `✗ ${S.fc.roundUnknown.size}`;
}

// ════════════════════════════════════════════
// WRITING MODE
// ════════════════════════════════════════════
function initWriting() {
  const wr = S.wr;
  const deck = unknown(wr.knownIds);
  if (deck.length === 0) { showAllDone('writing'); return; }

  wr.deck = shuffle(deck.map(w => w.id));
  wr.idx  = 0;

  showSection('secWriting');
  renderWR();
}

function renderWR() {
  const wr = S.wr;

  // Cycle deck when exhausted
  if (wr.idx >= wr.deck.length) {
    const remaining = unknown(wr.knownIds);
    if (remaining.length === 0) { showAllDone('writing'); return; }
    wr.deck = shuffle(remaining.map(w => w.id));
    wr.idx  = 0;
  }

  const word  = WORDS[wr.deck[wr.idx]];
  const enJp  = S.dir === 'en-jp';
  wr.firstAttempt = true;

  // Reset UI
  $('wrResult').style.display = 'none';
  const inp = $('wrInput');
  inp.value    = '';
  inp.className = 'wr-input';
  inp.disabled = false;
  $('wrCheck').style.display = '';
  inp.focus();

  $('wrQ').textContent = enJp ? word.en : word.ja;
  setBadge($('wrBadge'), word);

  // Speak button: visible when English is the question
  $('wrSpk').style.display = enJp ? 'flex' : 'none';
  if (enJp) speak(word.en);

  // Consecutive dots
  const c = wr.consecutive[word.id] || 0;
  $('dot0').className = 'dot' + (c >= 1 ? ' on' : '');
  $('dot1').className = 'dot' + (c >= 2 ? ' on' : '');

  setProgress(wr.knownIds.size, WORDS.length);
}

function checkWR() {
  const wr   = S.wr;
  const word = WORDS[wr.deck[wr.idx]];
  const enJp = S.dir === 'en-jp';
  const inp  = $('wrInput');
  const user = inp.value.trim();

  if (!user) return;

  const correct = enJp ? word.ja : word.en;
  const ok      = isMatch(user, correct, !enJp /* isEnglish */);

  inp.className = 'wr-input ' + (ok ? 'ok' : 'ng');
  inp.disabled  = true;
  $('wrCheck').style.display = 'none';

  const resText = $('wrResText');
  const resAns  = $('wrResAns');
  const spkWrap = $('wrResSpkWrap');

  if (ok) {
    if (wr.firstAttempt) {
      wr.consecutive[word.id] = (wr.consecutive[word.id] || 0) + 1;
      const c = wr.consecutive[word.id];
      if (c >= 2) {
        wr.knownIds.add(word.id);
        delete wr.consecutive[word.id];
        resText.textContent = '🏆 マスター！';
        resAns.textContent  = '2回連続1発正解で習得しました！';
      } else {
        resText.textContent = '✓ 正解！';
        resAns.textContent  = `あと1回連続正解でマスター (${c}/2)`;
      }
      // Update dots
      const nc = wr.consecutive[word.id] || 0;
      $('dot0').className = 'dot' + (nc >= 1 ? ' on' : '');
      $('dot1').className = 'dot' + (nc >= 2 ? ' on' : '');
    } else {
      resText.textContent = '✓ 正解！';
      resAns.textContent  = '（2回目の試み）';
    }
    resText.className = 'res-text ok';
    spkWrap.style.display = 'none';
  } else {
    wr.consecutive[word.id] = 0;
    wr.firstAttempt = false;
    resText.textContent = '✗ 不正解';
    resText.className   = 'res-text ng';
    resAns.textContent  = `正解: ${correct}`;

    // Show speak button with English word when answer revealed in JP→EN mode
    if (!enJp) {
      spkWrap.style.display = 'flex';
      speak(word.en);
    } else {
      spkWrap.style.display = 'none';
    }
    $('dot0').className = 'dot';
    $('dot1').className = 'dot';
  }

  $('wrResult').style.display = 'flex';
  persist();
}

function isMatch(user, correct, isEnglish) {
  const u = user.trim();
  const c = correct.trim();
  if (isEnglish) return u.toLowerCase() === c.toLowerCase();
  // Japanese: exact or any segment separated by ・ / 、 /
  if (u === c) return true;
  return c.split(/[・\/、]/).some(seg => u === seg.trim());
}

function nextWR() {
  S.wr.idx++;
  renderWR();
}

// ════════════════════════════════════════════
// ALL DONE
// ════════════════════════════════════════════
function showAllDone(mode) {
  showSection('secDone');
  $('doneRestart').onclick = () => {
    if (mode === 'flashcard') {
      S.fc.knownIds = new Set();
    } else {
      S.wr.knownIds     = new Set();
      S.wr.consecutive  = {};
    }
    persist();
    mode === 'flashcard' ? initFlashcard() : initWriting();
  };
}

// ════════════════════════════════════════════
// MODE / DIRECTION
// ════════════════════════════════════════════
function setMode(mode) {
  S.mode = mode;
  document.querySelectorAll('.mode-tab').forEach(t =>
    t.classList.toggle('active', t.dataset.mode === mode)
  );
  persist();
  mode === 'flashcard' ? initFlashcard() : initWriting();
}

function setDir(dir) {
  S.dir = dir;
  document.querySelectorAll('.dir-btn').forEach(b =>
    b.classList.toggle('active', b.dataset.dir === dir)
  );
  persist();
  S.mode === 'flashcard' ? initFlashcard() : initWriting();
}

// ════════════════════════════════════════════
// TOUCH SWIPE (flashcard)
// ════════════════════════════════════════════
let tx0 = 0, ty0 = 0;

function initSwipe() {
  const card = $('fcCard');
  card.addEventListener('touchstart', e => {
    tx0 = e.touches[0].clientX;
    ty0 = e.touches[0].clientY;
  }, { passive: true });
  card.addEventListener('touchend', e => {
    if (!S.fc.flipped) return;
    const dx = e.changedTouches[0].clientX - tx0;
    const dy = Math.abs(e.changedTouches[0].clientY - ty0);
    if (Math.abs(dx) > 55 && dy < 45) markFC(dx > 0);
  }, { passive: true });
}

// ════════════════════════════════════════════
// KEYBOARD
// ════════════════════════════════════════════
function initKeyboard() {
  document.addEventListener('keydown', e => {
    if (S.mode === 'flashcard') {
      if (e.code === 'Space' || e.code === 'ArrowDown') { e.preventDefault(); flipFC(); }
      if (e.code === 'ArrowRight' && S.fc.flipped) markFC(true);
      if (e.code === 'ArrowLeft'  && S.fc.flipped) markFC(false);
    }
    if (S.mode === 'writing') {
      if (e.code === 'Enter') {
        e.preventDefault();
        if ($('wrResult').style.display === 'none') checkWR();
        else nextWR();
      }
    }
  });
}

// ════════════════════════════════════════════
// EVENT LISTENERS
// ════════════════════════════════════════════
function bindEvents() {
  // Mode tabs
  document.querySelectorAll('.mode-tab').forEach(t =>
    t.addEventListener('click', () => setMode(t.dataset.mode))
  );

  // Direction
  document.querySelectorAll('.dir-btn').forEach(b =>
    b.addEventListener('click', () => setDir(b.dataset.dir))
  );

  // Flashcard card click (flip)
  $('fcCard').addEventListener('click', e => {
    if (!e.target.closest('.spk-btn') && !e.target.closest('.act-btn')) flipFC();
  });

  // Flashcard speak (front – English in en-jp mode)
  $('fcSpkFront').addEventListener('click', e => {
    e.stopPropagation();
    const w = WORDS[S.fc.deck[S.fc.idx]];
    if (w) speak(w.en);
  });

  // Flashcard speak (back – English in jp-en mode)
  $('fcSpkBack').addEventListener('click', e => {
    e.stopPropagation();
    const w = WORDS[S.fc.deck[S.fc.idx]];
    if (w) speak(w.en);
  });

  // Flashcard Yes / No
  $('fcYes').addEventListener('click', e => { e.stopPropagation(); if (S.fc.flipped) markFC(true);  });
  $('fcNo' ).addEventListener('click', e => { e.stopPropagation(); if (S.fc.flipped) markFC(false); });

  // Writing speak (question – only shown in en-jp)
  $('wrSpk').addEventListener('click', () => {
    const w = WORDS[S.wr.deck[S.wr.idx]];
    if (w) speak(w.en);
  });

  // Writing result speak
  $('wrResSpk').addEventListener('click', () => {
    const w = WORDS[S.wr.deck[S.wr.idx]];
    if (w) speak(w.en);
  });

  // Writing check / next
  $('wrCheck').addEventListener('click', checkWR);
  $('wrNext' ).addEventListener('click', nextWR);
  $('wrInput').addEventListener('keydown', e => {
    if (e.key === 'Enter') {
      e.preventDefault();
      if ($('wrResult').style.display === 'none') checkWR();
      else nextWR();
    }
  });

  initSwipe();
  initKeyboard();
}

// ════════════════════════════════════════════
// SERVICE WORKER
// ════════════════════════════════════════════
function registerSW() {
  if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register('sw.js').catch(() => {});
  }
}

// ════════════════════════════════════════════
// BOOT
// ════════════════════════════════════════════
document.addEventListener('DOMContentLoaded', () => {
  hydrate();

  // Sync UI to persisted state
  document.querySelectorAll('.mode-tab').forEach(t =>
    t.classList.toggle('active', t.dataset.mode === S.mode)
  );
  document.querySelectorAll('.dir-btn').forEach(b =>
    b.classList.toggle('active', b.dataset.dir === S.dir)
  );

  bindEvents();

  if (S.mode === 'flashcard') initFlashcard();
  else initWriting();

  registerSW();
});
