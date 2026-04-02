/* ============================================================
   common.js — Shared auth state, utilities, and header render
   ============================================================ */

/* ── Storage keys ─────────────────────────────────────────── */
const AUTH_KEY  = 'ems_auth';
const USER_KEY  = 'ems_user';
const ADMIN_KEY = 'ems_admin';

/* ── Auth accessors ───────────────────────────────────────── */
function getAuthHeader() { return sessionStorage.getItem(AUTH_KEY) || ''; }
function getUsername()   { return sessionStorage.getItem(USER_KEY)  || ''; }
function getIsAdmin()    { return sessionStorage.getItem(ADMIN_KEY) === 'true'; }

function saveAuth(authHeader, username, isAdmin) {
    sessionStorage.setItem(AUTH_KEY,  authHeader);
    sessionStorage.setItem(USER_KEY,  username);
    sessionStorage.setItem(ADMIN_KEY, String(isAdmin));
}

function clearAuth() {
    sessionStorage.removeItem(AUTH_KEY);
    sessionStorage.removeItem(USER_KEY);
    sessionStorage.removeItem(ADMIN_KEY);
}

/* ── Guard helpers ────────────────────────────────────────── */
/** Redirect to login if not authenticated. Returns false when redirecting. */
function checkAuth() {
    if (!getAuthHeader()) {
        window.location.replace('/index.html');
        return false;
    }
    return true;
}

/** Redirect to employees page if not admin (or to login if not authed). */
function checkAdminAuth() {
    if (!checkAuth()) return false;
    if (!getIsAdmin()) {
        window.location.replace('/employees.html');
        return false;
    }
    return true;
}

/* ── Logout ───────────────────────────────────────────────── */
function logout() {
    clearAuth();
    window.location.href = '/index.html';
}

/* ── Render shared header + nav ───────────────────────────── */
function renderHeader(containerId) {
    const username   = getUsername();
    const isAdm      = getIsAdmin();
    const currentPath = window.location.pathname;

    const navItems = [
        { href: '/employees.html',    label: '👥 All Employees', key: 'employees',    adminOnly: false },
        { href: '/add-employee.html', label: '➕ Add Employee',  key: 'add-employee', adminOnly: true  },
        { href: '/raise-salary.html', label: '💰 Raise Salary',  key: 'raise-salary', adminOnly: true  }
    ];

    const navHtml = navItems.map(item => {
        const isActive   = currentPath.includes(item.key);
        const isDisabled = item.adminOnly && !isAdm;
        const cls = ['nav-link', isActive ? 'active' : '', isDisabled ? 'disabled' : '']
            .filter(Boolean).join(' ');
        const title = isDisabled ? 'title="Admin only"' : '';
        return `<a href="${item.href}" class="${cls}" ${title}>${item.label}</a>`;
    }).join('');

    const badge = isAdm
        ? '<span class="role-badge admin">Admin</span>'
        : '<span class="role-badge">User</span>';

    document.getElementById(containerId).innerHTML = `
        <header class="app-header">
            <h1>🏢 Employee Management System</h1>
            <div class="user-info">
                <span>👤 <strong>${escHtml(username)}</strong></span>
                ${badge}
                <button class="btn-logout" onclick="logout()">Logout</button>
            </div>
        </header>
        <nav class="app-nav">
            ${navHtml}
        </nav>
    `;
}

/* ── Authenticated fetch ──────────────────────────────────── */
async function apiFetch(url, options = {}) {
    return fetch(url, {
        ...options,
        headers: {
            'Authorization': getAuthHeader(),
            'Content-Type':  'application/json',
            'Accept':        'application/json',
            ...(options.headers || {})
        }
    });
}

/* ── Parse response body ──────────────────────────────────── */
async function parseBody(res) {
    const text = await res.text();
    if (!text) return null;
    try { return JSON.parse(text); }
    catch { return text; }
}

/* ── Format error body into HTML ──────────────────────────── */
function formatError(data) {
    if (!data) return 'An unknown error occurred.';
    if (typeof data === 'string') return escHtml(data);
    if (typeof data === 'object') {
        return Object.entries(data)
            .map(([field, msg]) => `<strong>${escHtml(field)}</strong>: ${escHtml(String(msg))}`)
            .join('<br>');
    }
    return escHtml(String(data));
}

/* ── Alert helpers ────────────────────────────────────────── */
function showAlert(containerId, html, type = 'success') {
    const icons = { success: '✅', error: '❌', info: 'ℹ️' };
    document.getElementById(containerId).innerHTML =
        `<div class="alert alert-${type}">${icons[type] || ''} ${html}</div>`;
}
function clearMsg(id) {
    const el = document.getElementById(id);
    if (el) el.innerHTML = '';
}

/* ── HTML escaping ────────────────────────────────────────── */
function escHtml(str) {
    return String(str ?? '')
        .replace(/&/g, '&amp;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;')
        .replace(/"/g, '&quot;')
        .replace(/'/g, '&#39;');
}
function escAttr(str) {
    return String(str ?? '').replace(/'/g, "\\'");
}

