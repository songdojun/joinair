// 초기화 함수 (이 함수를 호출하여 페이지가 로드될 때 검색을 초기화)
function initializeSearch() {
    document.getElementById("searchButton").addEventListener("click", function () {
        performSearch();
    });

    document.getElementById("searchKeyword").addEventListener("keyup", function (event) {
        if (event.key === "Enter") {
            event.preventDefault(); // 기본 엔터 행동 막기
            performSearch();
        }
    });
}
// HTML 파일이 로드될 때 초기화 함수 호출
document.addEventListener("DOMContentLoaded", function () {
    initializeSearch();
});

// 검색 함수
function performSearch(page = 0) {
    const currentSearchKeyword = document.getElementById("searchKeyword").value;

    // Ajax 요청을 통해 검색 결과를 가져옵니다.
    const url = "/productbuy/list";
    const params = new URLSearchParams({
        "searchOption": "Pro_Name", // 상품명으로 고정
        "searchKeyword": currentSearchKeyword,
        "page": page
    });
    const fullUrl = url + "?" + params.toString();

    // Ajax 요청을 통해 검색 결과를 가져옵니다.
    fetch(fullUrl)
        .then(response => response.text())
        .then(data => {
            // 검색 결과를 받은 후, .row 요소에 추가합니다.
            const rowContainer = document.querySelector(".row");
            rowContainer.innerHTML = getProductsFromHTML(data);

            // 검색 결과를 받은 후 다시 초기화하여 검색을 가능하게 합니다.
            initializeSearch();
        })
        .catch(error => console.error("검색 오류: ", error));
}

// 받아온 HTML에서 상품 목록을 추출하는 함수
function getProductsFromHTML(html) {
    const parser = new DOMParser();
    const doc = parser.parseFromString(html, "text/html");
    const productsContainer = doc.querySelector(".row");
    return productsContainer.innerHTML;
}


function changeCategory(categoryId) {
    // 선택한 카테고리 ID를 이용하여 서버에 Ajax 요청을 보냅니다.
    const url = "/productbuy/list";
    const params = new URLSearchParams({
        "cateNo": categoryId
    });
    const fullUrl = url + "?" + params.toString();

    fetch(fullUrl)
        .then(response => response.text())
        .then(data => {
            // 서버로부터 받은 데이터를 이용하여 상품 목록을 업데이트합니다.
            // data 변수에 서버 응답 데이터가 들어옵니다.
            const productListContainer = document.querySelector(".container.mt-5");
            productListContainer.innerHTML = data; // 상품 목록 컨테이너를 업데이트

            // 검색 결과를 받은 후 다시 초기화하여 검색을 가능하게 합니다.
            initializeSearch();
        })
        .catch(error => console.error("카테고리 변경 오류: ", error));
}


