function employeeSubmit(){
axios.post('/employee', {
    firstName: document.getElementById('firstName').value,
    lastName: document.getElementById('lastName').value,
    email: document.getElementById('email').value,
    password: document.getElementById('password').value,
    phone: document.getElementById('phone').value,
    date: document.getElementById('date').value,

  })
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });
  document.getElementById("employeeform").reset();
  document.getElementById('successMessage').style.display = 'block';
}

document.addEventListener('DOMContentLoaded', function () {
  const loginForm = document.getElementById('loginForm');
  if (loginForm) {
      loginForm.addEventListener('submit', function (event) {
          event.preventDefault();

          const loginData = {
              email: document.getElementById('email').value,
              password: document.getElementById('password').value
          };

          axios.post('/loginform', loginData)
              .then(function (response) {
                  document.getElementById('loginMessage').innerText = response.data.message;
              })
              .catch(function (error) {
                  console.error(error);
              });
      });
  }
});
