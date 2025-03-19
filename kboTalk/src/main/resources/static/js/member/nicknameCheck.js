document.addEventListener("DOMContentLoaded", function () {
    var checkNicknameBtn = document.getElementById("checkNicknameBtn");
    var nicknameInput = document.getElementById("nickname");
    var signupForm = document.querySelector("form");
    var isNicknameValid = false; // 닉네임 중복 여부를 저장하는 변수

    // 닉네임 중복 확인 버튼 클릭 시
    checkNicknameBtn.addEventListener("click", function () {
        var nickname = nicknameInput.value.trim();

        if (nickname === "") {
            alert("닉네임을 입력하세요.");
            return;
        }

        fetch("/api/members/checkNickname?nickname=" + encodeURIComponent(nickname))
            .then(response => {
                if (response.ok) {
                    return response.text(); // 서버 응답이 JSON이 아닐 수도 있으므로 text() 사용
                } else if (response.status === 400) {
                    return response.text().then(text => { throw new Error(text); });
                } else {
                    throw new Error("오류가 발생했습니다. 다시 입력하세요.");
                }
            })
            .then(data => {
                alert("✅ 사용 가능한 닉네임입니다!");
                isNicknameValid = true; // 사용 가능한 닉네임으로 설정
            })
            .catch(error => {
                alert("❌ 이미 존재하는 닉네임입니다. 다른 닉네임을 사용하세요.");
                isNicknameValid = false; // 중복된 닉네임일 경우 false 설정
            });
    });

    // 회원가입 버튼 클릭 시 닉네임 중복 검사
    signupForm.addEventListener("submit", function (event) {
        if (!isNicknameValid) { // 닉네임이 중복이라면
            event.preventDefault(); // 폼 제출 방지
            alert("❌ 닉네임 오류 발생. 닉네임을 다시 입력하세요.");
            nicknameInput.focus(); // 닉네임 입력창으로 포커스 이동
        }
    });

    // 닉네임 입력 시 중복 확인 초기화
    nicknameInput.addEventListener("input", function () {
        isNicknameValid = false; // 닉네임이 변경되면 다시 중복 확인해야 함
    });
});
