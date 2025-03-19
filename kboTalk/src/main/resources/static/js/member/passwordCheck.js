document.addEventListener("DOMContentLoaded", function () {
    const passwordInput = document.getElementById("password");
    const checkPasswordInput = document.getElementById("checkPassword");
    const passwordMessage = document.createElement("div");
    const submitButton = document.querySelector("button[type='submit']");

    passwordMessage.style.fontSize = "0.9em";
    passwordMessage.style.marginTop = "5px";
    checkPasswordInput.parentNode.appendChild(passwordMessage);

    function checkPasswordMatch() {
        if (checkPasswordInput.value === "") {
            passwordMessage.textContent = "";
            submitButton.disabled = true;
            return;
        }
        if (passwordInput.value === checkPasswordInput.value) {
            passwordMessage.textContent = "✅ 비밀번호가 일치합니다";
            passwordMessage.style.color = "green";
            submitButton.disabled = false; // 회원가입 버튼 활성화
        } else {
            passwordMessage.textContent = "❌ 비밀번호가 일치하지 않습니다";
            passwordMessage.style.color = "red";
            submitButton.disabled = true; // 회원가입 버튼 비활성화
        }
    }

    // 비밀번호 입력이 변경될 때마다 checkPasswordMatch() 실행
    passwordInput.addEventListener("input", checkPasswordMatch);
    // 비밀번호 확인 입력이 변경될 때마다 checkPasswordMatch() 실행
    checkPasswordInput.addEventListener("input", checkPasswordMatch);
});
