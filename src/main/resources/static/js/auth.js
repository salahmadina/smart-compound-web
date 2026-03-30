// If already logged in, redirect away from login page
const existingUser = getSessionUser();
if (existingUser) {
  window.location.href = existingUser.role === 'STAFF'
    ? '/staff/dashboard.html'
    : '/resident/dashboard.html';
}

// Tab switching
document.querySelectorAll('.auth-tab').forEach(tab => {
  tab.addEventListener('click', () => {
    document.querySelectorAll('.auth-tab').forEach(t => t.classList.remove('active'));
    document.querySelectorAll('.auth-form').forEach(f => f.classList.remove('active'));
    tab.classList.add('active');
    document.getElementById(tab.dataset.form).classList.add('active');
  });
});

// ── Login ──
document.getElementById('loginForm').addEventListener('submit', async (e) => {
  e.preventDefault();
  const alert = document.getElementById('loginAlert');
  const btn   = document.getElementById('loginBtn');
  btn.disabled = true;
  btn.textContent = 'Signing in…';

  const data = await apiFetch('/auth/login', {
    method: 'POST',
    body: JSON.stringify({
      username: document.getElementById('loginUsername').value.trim(),
      password: document.getElementById('loginPassword').value,
    }),
  });

  btn.disabled = false;
  btn.textContent = 'Sign In';

  if (!data) return;
  if (!data.success) { showAlert(alert, data.message); return; }

  sessionStorage.setItem('user', JSON.stringify(data.data));
  window.location.href = data.data.role === 'STAFF'
    ? '/staff/dashboard.html'
    : '/resident/dashboard.html';
});

// ── Signup ──
document.getElementById('signupForm').addEventListener('submit', async (e) => {
  e.preventDefault();
  const alert = document.getElementById('signupAlert');
  const btn   = document.getElementById('signupBtn');

  const role = document.getElementById('signupRole').value;
  const body = {
    username:   document.getElementById('signupUsername').value.trim(),
    password:   document.getElementById('signupPassword').value,
    fullName:   document.getElementById('signupFullName').value.trim(),
    email:      document.getElementById('signupEmail').value.trim(),
    phone:      document.getElementById('signupPhone').value.trim(),
    nationalId: document.getElementById('signupNationalId').value.trim(),
    age:        parseInt(document.getElementById('signupAge').value),
    role,
    dependants: document.getElementById('signupDependants').value.trim() || null,
  };

  if (role === 'RESIDENT') {
    body.apartmentNum = parseInt(document.getElementById('signupApartment').value) || null;
    body.buildingNum  = parseInt(document.getElementById('signupBuilding').value)  || null;
  }

  btn.disabled = true;
  btn.textContent = 'Creating account…';

  const data = await apiFetch('/auth/signup', { method: 'POST', body: JSON.stringify(body) });

  btn.disabled = false;
  btn.textContent = 'Create Account';

  if (!data) return;
  if (!data.success) { showAlert(alert, data.message); return; }

  showAlert(alert, 'Account created! You can now log in.', 'success');
  // Switch to login tab
  document.querySelector('[data-form="loginForm"]').click();
});

// Show/hide resident fields based on role selection
document.getElementById('signupRole').addEventListener('change', function () {
  const resFields = document.getElementById('residentFields');
  resFields.style.display = this.value === 'RESIDENT' ? 'block' : 'none';
});
