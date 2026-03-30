// Central fetch wrapper — all API calls go through here
async function apiFetch(path, options = {}) {
  const res = await fetch('/api' + path, {
    credentials: 'include',
    headers: { 'Content-Type': 'application/json', ...(options.headers || {}) },
    ...options,
  });

  if (res.status === 401) {
    sessionStorage.clear();
    window.location.href = '/index.html';
    return null;
  }

  return res.json();
}

// Show an alert element
function showAlert(el, message, type = 'error') {
  el.textContent = message;
  el.className = `alert alert-${type} show`;
  setTimeout(() => el.classList.remove('show'), 5000);
}

// Get current user from session storage
function getSessionUser() {
  const raw = sessionStorage.getItem('user');
  return raw ? JSON.parse(raw) : null;
}

// Role guard — call at top of every protected page
function requireRole(role) {
  const user = getSessionUser();
  if (!user) { window.location.href = '/index.html'; return null; }
  if (role && user.role !== role) {
    window.location.href = user.role === 'STAFF' ? '/staff/dashboard.html' : '/resident/dashboard.html';
    return null;
  }
  return user;
}

// Format datetime
function fmtDate(iso) {
  if (!iso) return '-';
  return new Date(iso).toLocaleString('en-GB', { dateStyle: 'medium', timeStyle: 'short' });
}

// Build a badge span
function badge(status) {
  const s = (status || '').toLowerCase().replace(/ /g, '_');
  return `<span class="badge badge-${s}">${status}</span>`;
}

// Build an empty-state div
function emptyState(icon, msg) {
  return `<div class="empty-state"><div class="icon">${icon}</div><p>${msg}</p></div>`;
}

// Logout
async function logout() {
  await apiFetch('/auth/logout', { method: 'POST' });
  sessionStorage.clear();
  window.location.href = '/index.html';
}
