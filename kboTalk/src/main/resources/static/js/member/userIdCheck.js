document.addEventListener("DOMContentLoaded", function () {
    var checkNicknameBtn = document.getElementById("checkUserIdBtn");
    var userIdInput = document.getElementById("userId");
    var signupForm = document.querySelector("form");
    var isUserIdValid = false; // ID 중복 여부를 저장하는 변수

    // ID 중복 확인 버튼 클릭 시
    checkNicknameBtn.addEventListener("click", function () {
        var userId = userIdInput.value.trim();

        if (userId === "") {
            alert("ID를 입력하세요.");
            return;
        }

        fetch("/api/members/checkUserId?userId=" + encodeURIComponent(userId))
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
                alert("✅ 사용 가능한 ID입니다!");
                isUserIdValid = true; // 사용 가능한 ID로 설정
            })
            .catch(error => {
                alert("❌ 이미 존재하는 ID입니다. 다른 ID을 사용하세요.");
                isUserIdValid = false; // 중복된 ID일 경우 false 설정
            });
    });

    // 회원가입 버튼 클릭 시 닉네임 중복 검사
    signupForm.addEventListener("submit", function (event) {
        if (!isUserIdValid) { // ID가 중복이라면
            event.preventDefault(); // 폼 제출 방지
            alert("❌ ID 오류 발생. ID를 다시 입력하세요.");
            nicknameInput.focus(); // ID 입력창으로 포커스 이동
        }
    });

    // 닉네임 입력 시 중복 확인 초기화
    nicknameInput.addEventListener("input", function () {
        isUserIdValid = false; // ID가 변경되면 다시 중복 확인해야 함
    });
});