$(document).ready(function () {
    $("#checkNicknameBtn").click(function () {
        var nickname = $("#nickname").val().trim();
        var errorDiv = $("#nicknameError");

        if (nickname === "") {
            errorDiv.text("닉네임을 입력하세요.").css("color", "red");
            return;
        }

        $.get("/api/members/checkNickname", { nickname: nickname })
            .done(function (data) {
                alert("사용 가능한 닉네임입니다.")
//                errorDiv.text("사용 가능한 닉네임입니다.").css("color", "green");
            })
            .fail(function (jqXHR) {
                if (jqXHR.status === 400) {
                    alert("이미 존재하는 닉네임입니다. 다른 닉네임을 사용하세요.")
//                    errorDiv.text(jqXHR.responseText).css("color", "red"); // 서버에서 보낸 메시지 표시
                } else {
                    alert("오류가 발생했습니다. 다시 입력하세요.")
//                    errorDiv.text("확인 중 오류가 발생했습니다.").css("color", "red");
                }
            });
    });

    // 닉네임 입력 시 오류 메시지 초기화
    $("#nickname").on("input", function () {
        $("#nicknameError").text("");
    });
});