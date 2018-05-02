$(document).ready(function () {
    $("#signin").click(function () {
        $("#sign_in").modal({backdrop: true});
    });
});

$(document).ready(function () {
    $("#signup").click(function () {
        $("#sign_up").modal({backdrop: true});
    });
});

//Валидация формы входа
$(document).ready(function ($) {
    $('form#sign-in-form').on('click', function (e) {

// Запрещаем стандартное поведение для кнопки submit
        var loginReg = new RegExp('^([a-zA-Z][a-zA-Z-_0-9]+)$');
        var passwordReg = new RegExp('[a-zA-Z-_0-9]{6,}');
        var $form_modal = $('.cd-user-modal');
        var $form_sign_in = $form_modal.find('#sign_in');
        var $numberCorrectField = 0;

        var password = $('input#inputPassword').val();
        var login = $('input#inputLogin').val();

        if (password.length >= 4 && password != '' && passwordReg.test(password)) {
            $('input#inputPassword').css('border-color', 'green');
            $('input#inputPassword').removeClass('has-error').next('span#password-span').removeClass('is-visible');
            $numberCorrectField++;
        }
        else {
            if (password.length != 0) {
                $('input#inputPassword').css('border-color', 'red');
                $('input#inputPassword').addClass('has-error').next('span#password-span').addClass('is-visible');

            }
        }
        if (login.length > 3 && login != '' && loginReg.test(login)) {
            $('input#inputLogin').css('border-color', 'green');
            $('input#inputLogin').removeClass('has-error').next('span#login-span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (login.length != 0) {
                $('input#inputLogin').css('border-color', 'red');
                $('input#inputLogin').addClass('has-error').next('span#login-span').addClass('is-visible');
            }
        }
        if ($numberCorrectField != 2) {
            e.preventDefault();
        }
    });
});


//Валидация формы регистрации
$(document).ready(function ($) {
    $('form#sign-up-form').on('click', function (e) {

// Запрещаем стандартное поведение для кнопки submit
        var name_surnameReg = new RegExp("^([\u{0410}-\u{042F}]{1}[\u{0430}-\u{044F}]+)$|^([A-Z]{1}[a-z]+)$");
        var emailReg = new RegExp("^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(?:\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$");
        var loginReg = new RegExp('^([a-zA-Z][a-zA-Z-_0-9]+)$');
        var passwordReg = new RegExp('[a-zA-Z-_0-9]{6,}');

        var password = $('input#password').val();
        var login = $('input#login').val();
        var name = $('input#name').val();
        var surname = $('input#surname').val();
        var email = $('input#email').val();
        var repassword = $('input#re-password').val();

        var $numberCorrectField = 0;

        if (password.length >= 4 && passwordReg.test(password)) {
            $('input#password').css('border-color', 'green');
            $('input#password').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (password.length != 0) {
                $('input#password').css('border-color', 'red');
                $('input#password').addClass('has-error').next('span').addClass('is-visible');
            }
        }


        if (login.length >= 3 && loginReg.test(login)) {
            $('input#login').css('border-color', 'green');
            $('input#login').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (login.length != 0) {
                $('input#login').css('border-color', 'red');
                $('input#login').addClass('has-error').next('span').addClass('is-visible');
            }
        }


        if (name.length >= 2 && name_surnameReg.test(name)) {
            $('input#name').css('border-color', 'green');
            $('input#name').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (name.length != 0) {
                $('input#name').css('border-color', 'red');
                $('input#name').addClass('has-error').next('span').addClass('is-visible');
            }
        }


        if (surname.length >= 2 && name_surnameReg.test(surname)) {
            $('input#surname').css('border-color', 'green');
            $('input#surname').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (surname.length != 0) {
                $('input#surname').css('border-color', 'red');
                $('input#surname').addClass('has-error').next('span').addClass('is-visible');
            }
        }


        if (email.length > 7 && email != '' && emailReg.test(email)) {
            $('input#email').css('border-color', 'green');
            $('input#email').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (email.length != 0) {
                $('input#email').css('border-color', 'red');
                $('input#email').addClass('has-error').next('span').addClass('is-visible');
            }
        }

        if ($numberCorrectField != 6) {
            e.preventDefault();
        }
    });
});
