//Валидация формы входа
$(document).ready(function () {
    $("#signin").click(function () {
        $("#sign_in").modal({backdrop: true});
    });
    $("#signup").click(function () {
        $("#sign_up").modal({backdrop: true});
    });
    $("#addadmin").click(function () {
        $("#add_admin").modal({backdrop: true});
    });
    $("#addproduct").click(function () {
        $("#add_product").modal({backdrop: true});
    });

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

        if ($numberCorrectField != 5) {
            e.preventDefault();
        }
    });


    $('form#add-product-form').on('click', function (e) {

        var name_ruReg = new RegExp('^([\u{0410}-\u{042F}]{1}[\u{0430}-\u{044F}\u{0410}-\u{042F}\\-\\s\\`]+)$');
        var name_enReg = new RegExp('^([A-Z]{1}[a-zA-Z-\\s\\-\\`]+)$');
        var costweightReg = new RegExp('^(([0-9]+)(\\.){0,1}([0-9]+))$');

        var name_ru = $('input#nameRu').val();
        var name_en = $('input#nameEn').val();
        var weight = $('input#weight').val();
        var cost = $('input#cost').val();
        var image_name = $('input#image').val();
        var $numberCorrectField = 0;

        if (name_ru.length >= 2 && name_ru != '' && name_ruReg.test(name_ru)) {
            $('input#nameRu').css('border-color', 'green');
            $('#name-ru').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (name_ru.length != 0) {
                $('input#nameRu').css('border-color', 'red');
                $('#name-ru').addClass('is-visible');
            }
        }

        if (name_en.length >= 2 && name_en != '' && name_enReg.test(name_en)) {
            $('input#nameEn').css('border-color', 'green');
            $('#name-en').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (name_ru.length != 0) {
                $('input#nameEn').css('border-color', 'red');
                $('#name-en').addClass('is-visible');
            }
        }

        if (cost != '' && costweightReg.test(cost)) {
            $('input#cost').css('border-color', 'green');
            $('#cost-span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (cost.length != 0) {
                $('input#cost').css('border-color', 'red');
                $('#cost-span').addClass('is-visible');
            }
        }

        if (weight != '' && costweightReg.test(weight)) {
            $('input#weight').css('border-color', 'green');
            $('#weight-span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (weight.length != 0) {
                $('input#weight').css('border-color', 'red');
                $('#weight-span').addClass('is-visible');
            }
        }

        if (image_name != "\u0412\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u0444\u0430\u0439\u043B") {
            $('input#image').css('border-color', 'green');
            $('#image-span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#image').css('border-color', 'red');
            $('#image-span').addClass('is-visible');
        }

        if ($numberCorrectField != 5) {
            e.preventDefault();
        }
    });

    $('form#add-admin-form').on('click', function (e) {
        var loginReg = new RegExp('^([a-zA-Z][a-zA-Z-_0-9]+)$');
        var passwordReg = new RegExp('[a-zA-Z-_0-9]{6,}');

        var password = $('input#passwordAdmin').val();
        var repassword = $('input#repasswordAdmin').val();
        var login = $('input#loginAdmin').val();
        var $numberCorrectField = 0;
        var $numberCorrectPassword = 0;


        if (password.length >= 4 && password != '' && passwordReg.test(password)) {
            $('input#passwordAdmin').css('border-color', 'green');
            $('input#passwordAdmin').removeClass('has-error').next('span#password-admin').removeClass('is-visible');
            $numberCorrectField++;
            $numberCorrectPassword++;
        }
        else {
            if (password.length != 0) {
                $('input#passwordAdmin').css('border-color', 'red');
                $('input#passwordAdmin').addClass('has-error').next('span#password-admin').addClass('is-visible');
            }
        }

        if (repassword.length >= 4 && password != '' && passwordReg.test(repassword)) {
            $('input#repasswordAdmin').css('border-color', 'green');
            $('input#repasswordAdmin').removeClass('has-error').next('span#repassword-admin').removeClass('is-visible');
            $numberCorrectField++;
            $numberCorrectPassword++;
        }
        else {
            if (repassword.length != 0) {
                $('input#repasswordAdmin').css('border-color', 'red');
                $('input#repasswordAdmin').addClass('has-error').next('span#repassword-admin').addClass('is-visible');
            }
        }

        if($numberCorrectPassword==2){
            if(repassword!=password){
                $('input#repasswordAdmin').css('border-color', 'red')
                $('input#passwordAdmin').css('border-color', 'red');
                $('input#repasswordAdmin').addClass('has-error');
                $('span#repassword-confirm').addClass('is-visible');
            }
            else{
                $('input#repasswordAdmin').css('border-color', 'green')
                $('input#passwordAdmin').css('border-color', 'green');
                $('input#repasswordAdmin').removeClass('has-error');
                $('span#repassword-confirm').removeClass('is-visible');
                $numberCorrectField++;
            }
        }

        if (login.length > 3 && login != '' && loginReg.test(login)) {
            $('input#loginAdmin').css('border-color', 'green');
            $('input#loginAdmin').removeClass('has-error').next('span#login-admin').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (login.length != 0) {
                $('input#loginAdmin').css('border-color', 'red');
                $('input#loginAdmin').addClass('has-error').next('span#login-admin').addClass('is-visible');
            }
        }
        if ($numberCorrectField != 4) {
            e.preventDefault();
        }

    });


    $('form#profile-form').on('click', function (e) {

// Запрещаем стандартное поведение для кнопки submit
        var name_surnameReg = new RegExp("^([\u{0410}-\u{042F}]{1}[\u{0430}-\u{044F}]+)$|^([A-Z]{1}[a-z]+)$");
        var emailReg = new RegExp("^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(?:\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$");

        var name = $('input#profile-name').val();
        var surname = $('input#profile-surname').val();
        var email = $('input#profile-email').val();

        var $numberCorrectField = 0;


        if (name.length >= 2 && name_surnameReg.test(name)) {
            $('input#profile-name').css('border-color', 'green');
            $('input#profile-name').removeClass('has-error').next('span#name-profile-span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (name.length != 0) {
                $('input#profile-name').css('border-color', 'red');
                $('input#profile-name').addClass('has-error').next('span#name-profile-span').addClass('is-visible');
            }
        }


        if (surname.length >= 2 && name_surnameReg.test(surname)) {
            $('input#profile-surname').css('border-color', 'green');
            $('input#profile-surname').removeClass('has-error').next('span#surname-profile-span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (surname.length != 0) {
                $('input#profile-surname').css('border-color', 'red');
                $('input#profile-surname').addClass('has-error').next('span#surname-profile-span').addClass('is-visible');
            }
        }


        if (email.length > 7 && email != '' && emailReg.test(email)) {
            $('input#profile-email').css('border-color', 'green');
            $('input#profile-email').removeClass('has-error').next('span#email-profile-span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (email.length != 0) {
                $('input#profile-email').css('border-color', 'red');
                $('input#profile-email').addClass('has-error').next('span#email-profile-span').addClass('is-visible');
            }
        }

        if ($numberCorrectField != 3) {
            e.preventDefault();
        }
    });

});
