document.addEventListener("DOMContentLoaded", function () {
    const editor = document.querySelector('#summernote');
    if (!editor) return;

    $('#summernote').summernote({
        placeholder: '본문 내용을 입력하세요...',
        tabsize: 2,
        height: 300,
        toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'underline', 'clear']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['insert', ['link', 'picture', 'video']],
            ['view', ['fullscreen', 'codeview', 'help']]
        ],
        callbacks: {
            onImageUpload: function (files) {
                uploadImage(files[0], function (imageUrl) {
                    $('#summernote').summernote('insertImage', imageUrl, function ($image) { // 서버에서 리턴한 이미지 url을 summernote 에디터에 삽입
                        $image.attr('alt', '첨부 이미지');
                    });
                });
            },
            onMediaDelete: function ($target){
                const imageUrl = $target.attr('src');
                deleteImage(imageUrl);
            }
        }
    });
});
