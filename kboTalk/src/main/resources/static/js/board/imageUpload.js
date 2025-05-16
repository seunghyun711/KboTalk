function uploadImage(file, onSuccess, onError) {
    const formData = new FormData();
    formData.append("file", file);

    fetch("/api/board/uploadImage", {
        method: "POST",
        body: formData,
    })
    .then(async response => {
        if (!response.ok) {
            // JSON으로 에러 메시지 파싱
            const errorData = await response.json().catch(() => null);
            const message = (errorData && errorData.message) || "이미지 업로드 실패 (상태코드 : ${response.status})";
            throw new Error(message);
        }
        return response.text(); // 이미지 URL
    })
    .then(imageUrl => {
        onSuccess(imageUrl);
    })
    .catch(error => {
        if (onError) {
            onError(error);
        } else {
            alert(error.message);
        }
    });
}
