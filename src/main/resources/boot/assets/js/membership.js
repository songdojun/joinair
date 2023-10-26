

                            function validateAndSubmit() {
                                // 각 입력 필드로부터 값을 가져옵니다.
                                var userId = document.getElementById("userId").value;
                                var userName = document.getElementById("userName").value;
                                var userEmail = document.getElementById("userEmail").value;
                                var emailDomain = document.getElementById("emailDomain").value;
                                var userPassword = document.getElementById("userPassword").value;
                                var userPasswordCheck = document.getElementById("userPasswordCheck").value;
                                var userAddress = document.getElementById("sample2_postcode").value;
                                var userDetailAddress = document.getElementById("sample4_detailAddress").value;

                             //이메일 확인하



                                // 1. 아이디 필드가 비어있는지 확인
                                if (!userId) {
                                    alert("아이디를 입력하세요.");
                                    return false;  // 가입 실패
                                }

                                // 2. 이름 필드가 비어있는지 확인
                                if (!userName) {
                                    alert("이름을 입력하세요.");
                                    return false;  // 가입 실패
                                }

                                // 3. 이메일 필드가 비어있는지, 그리고 이메일 주소 형식이 올바른지 확인
                                if (!userEmail || !emailDomain) {
                                    alert("이메일을 입력하세요.");
                                    return false;  // 가입 실패
                                }
                                if (!emailPattern.test(userEmail + "@" + emailDomain)) {
                                    alert("올바른 이메일 주소 형식이 아닙니다. 이메일 주소를 다시 입력하세요.");
                                    return false;  // 가입 실패
                                }

                                // 4. 비밀번호 필드가 비어있는지 확인
                                if (!userPassword) {
                                    alert("비밀번호를 입력하세요.");
                                    return false;  // 가입 실패
                                }

                                // 5. 비밀번호와 비밀번호 확인이 일치하는지 확인
                                if (userPassword !== userPasswordCheck) {
                                    alert("비밀번호가 일치하지 않습니다.");
                                    return false;  // 가입 실패
                                }

                                // 6. 주소 필드가 비어있는지 확인
                                if (!userAddress) {
                                    alert("주소를 입력하세요.");
                                    return false;  // 가입 실패
                                }

                                // 7. 상세 주소 필드가 비어있는지 확인
                                if (!userDetailAddress) {
                                    alert("상세 주소를 입력하세요.");
                                    return false;  // 가입 실패
                                }

                                // 모든 가입 조건이 충족되면 다음 페이지로 이동
                                window.location.href = "../boot/index.html";
                                return true;  // 가입 성공
                            }
                    function validateEmail() {
                            var emailInput = document.getElementById("userEmail");
                            var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

                            if (!emailPattern.test(emailInput.value)) {
                                alert("올바른 이메일 주소 형식이 아닙니다. 이메일 주소를 다시 입력하세요.");
                                emailInput.focus();
                                return false;
                            }
                            return true;
                        }

                        function validatePassword() {
                            var passwordInput = document.getElementById("userPassword");
                            var passwordCheckInput = document.getElementById("userPasswordCheck");

                            if (passwordInput.value !== passwordCheckInput.value) {
                                alert("비밀번호가 일치하지 않습니다. 다시 입력하세요.");
                                passwordInput.value = "";
                                passwordCheckInput.value = "";
                                passwordInput.focus();
                                return false;
                            }
                            return true;
                        }
                           // updateEmail 함수 정의
                           function updateEmail() {
                               // 이메일 도메인 선택 드롭다운 요소를 가져와 domainDropdown 변수에 할당
                               var domainDropdown = document.getElementById("emailDomainDropdown");

                               // 사용자가 선택한 도메인을 가져오기 위해 현재 선택된 옵션의 값을 selectedDomain 변수에 할당
                               var selectedDomain = domainDropdown.options[domainDropdown.selectedIndex].value;

                               // 이메일 도메인 입력 필드 요소를 가져와 emailDomainInput 변수에 할당
                               var emailDomainInput = document.getElementById("emailDomain");

                               // 사용자가 선택한 도메인(selectedDomain)을 이메일 도메인 입력 필드(emailDomainInput)의 값으로 설정
                               emailDomainInput.value = selectedDomain;
                           }