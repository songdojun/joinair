/* signInUp.js */


    function mergeAddressesAndSubmit() {
    var postcode = document.getElementById('sample2_postcode').value;
    var detailAddress = document.getElementById('sample2_detailAddress').value;

    // 주소와 상세 주소를 합친 값을 `User_DetailAddress`에 넣음
    document.SignupForm.User_DetailAddress.value = postcode + ' ' + detailAddress;

    // `User_Address` 필드에 빈 문자열 또는 다른 값으로 초기화
    document.SignupForm.User_Address.value = ''; // 또는 다른 초기화 값

    document.SignupForm.submit();
}
</script>

<script>
    var detailAddressSet = false;  // 변수 추가

    function sample2_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var userAddress = data.roadAddress;
                var detailAddress = document.getElementById('sample2_detailAddress').value;

                if (detailAddress && !detailAddressSet) {
                    userAddress += ' ' + detailAddress;
                    detailAddressSet = true;  // 상세 주소가 설정되었음을 표시
                }

                document.getElementById('sample2_postcode').value = userAddress;
            },
            width: '100%',
            height: '100%',
            maxSuggestItems: 5
        }).open();
    }
</script>

<script>
    $(document).ready(function() {
        $("#userPassword, #userPasswordCheck").on("input", function() {
            var password = $("#userPassword").val();
            var passwordCheck = $("#userPasswordCheck").val();

            var passmessage = $("#passmessage");

            if (password === passwordCheck) {
                passmessage.text("패스워드 일치");
                passmessage.removeClass("error-message").addClass("success-message");
            } else {
                passmessage.text("패스워드 불일치");
                passmessage.removeClass("success-message").addClass("error-message");
            }
        });
    });
</script>

<script>
    // 페이지 로딩 시 실행
    $(document).ready(function() {
        $("#userId").on("input", function() {
            var userId = $(this).val();
            $.ajax({
                type: "POST",
                url: "/check-duplicate",
                data: { User_Id: userId },
                success: function(response) {
                    if (response === "duplicate") {
                        $("#message").text("이미 사용 중인 아이디입니다.");
                    } else {
                        $("#message").text("사용 가능한 아이디입니다.");
                    }
                }
            });
        });
    });
</script>

<script>
    const time_to_show_login = 400;
    const time_to_hidden_login = 200;

    function change_to_login() {
        document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_login";
        document.querySelector('.cont_form_login').style.display = "block";
        document.querySelector('.cont_form_sign_up').style.opacity = "0";

        setTimeout(function() {
            document.querySelector('.cont_form_login').style.opacity = "1";
        }, time_to_show_login);
        document.querySelector('.cont_form_login').style.top = "-100";

        setTimeout(function() {
            document.querySelector('.cont_form_sign_up').style.display = "none";
            document.querySelector('.cont_form_sign_up').style.top = -100;
        }, time_to_hidden_login);
    }

    const time_to_show_sign_up = 100;
    const time_to_hidden_sign_up = 400;

    function change_to_sign_up(at) {
        document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_sign_up";
        document.querySelector('.cont_form_sign_up').style.display = "block";
        document.querySelector('.cont_form_sign_up #message').style.display = "block";
        document.querySelector('.cont_form_login').style.opacity = "0";

        setTimeout(function() {
            document.querySelector('.cont_form_sign_up').style.opacity = "1";
        }, time_to_show_sign_up);

        setTimeout(function() {
            document.querySelector('.cont_form_login').style.display = "none";
        }, time_to_hidden_sign_up);
    }

    const time_to_hidden_all = 500;

    function hidden_login_and_sign_up() {
        document.querySelector('.cont_forms').className = "cont_forms";
        document.querySelector('.cont_form_sign_up').style.opacity = "0";
        document.querySelector('.cont_form_login').style.opacity = "0";

        setTimeout(function() {
            document.querySelector('.cont_form_sign_up').style.display = "none";
            document.querySelector('.cont_form_login').style.display = "none";
        }, time_to_hidden_all);
    }
</script>