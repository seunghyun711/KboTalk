function uploadImage(file, onSuccess, onError){
    const formData = new FormData();
    formData.append("file", file);

    fetch("/api/board/uploadImage", {
        method: "POST",
        body: formData,
    })
    .then(response => {
        if(!response.ok) throw new Error("이미지 업로드 실패");
        return response.text(); // 이미지 URL
    })
    .then(imageUrl => {
        onSuccess(imageUrl);
    })
    .catch(error => {
        if(onError){
            onError(error);
        }else{
            alert(error.message);
        }
    });

}