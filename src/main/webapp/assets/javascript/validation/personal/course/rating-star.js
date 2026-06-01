// function validateRating(input) {
//     if (input.value < 0) input.value = 0;
//     if (input.value > 5) input.value = 5;
//     let displayValue = input.value === '' ? 0 : input.value;
//     document.getElementById('ratingDisplay').innerText = displayValue;
// }
//
// $(document).ready(function () {
//     Validator.setupAutoClearErrors();
//
//     $('#myForm').on('submit', function (e) {
//         let comment = $('#user_comment').val().trim();
//         let rating = $('#ratingInput').val().trim();
//         let isValid = true;
//
//
//         if (comment === "") {
//             $('#error_comment').text("Vui lòng nhập bình luận!");
//             isValid = false;
//         }
//         if (rating < 1) {
//             $('#error_rating').text("Vui lòng đánh giá sao lớn hơn 1");
//             isValid = false;
//         }
//         if (!isValid) {
//             e.preventDefault();
//         }
//         return isValid;
//     });
//
// });
