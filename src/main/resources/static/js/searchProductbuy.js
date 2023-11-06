// 초기화 함수 (이 함수를 호출하여 페이지가 로드될 때 검색 및 페이징을 초기화)
function initializeSearch() {
    document.getElementById("searchButton").addEventListener("click", function () {
        performSearch(0); // 검색할 때 페이지 번호를 0으로 초기화
    });

    document.getElementById("searchKeyword").addEventListener("keyup", function (event) {
        if (event.key === "Enter") {
            event.preventDefault(); // 기본 엔터 행동 막기
            performSearch(0); // 검색할 때 페이지 번호를 0으로 초기화
        }
    });
}

// 페이지 로드 시 초기화 함수 호출
document.addEventListener("DOMContentLoaded", function () {
    initializeSearch();
});

// 검색 함수
function performSearch(page = 0) {
    const currentSearchKeyword = document.getElementById("searchKeyword").value;
    const currentSearchOption = "Pro_Name";  // 변경된 부분: 검색 옵션을 설정합니다.

    // Ajax 요청을 통해 검색 결과를 가져옵니다.
    const url = "/productbuy/list";
    const params = new URLSearchParams({
        "searchOption": currentSearchOption,
        "searchKeyword": currentSearchKeyword,
        "page": page
    });
    const fullUrl = url + "?" + params.toString();

    // Ajax 요청을 통해 검색 결과를 가져옵니다.
    fetch(fullUrl)
        .then(response => response.text())
        .then(data => {
            // 검색 결과를 받은 후, 실제로 상품 목록이 들어있는 부분을 지정
            const productListContainer = document.querySelector(".row");
            productListContainer.innerHTML = getProductsFromHTML(data);

            // 페이징 버튼들을 다시 초기화하여 클릭 이벤트 핸들러를 설정합니다.
            initializePagination(data); // 수정된 부분: 검색 결과에 따라 페이징 버튼을 동적으로 생성
        })
        .catch(error => console.error("검색 오류: ", error));
}

// 받아온 HTML에서 상품 목록과 페이징 버튼을 추출하는 함수
function getProductsFromHTML(html) {
    const parser = new DOMParser();
    const doc = parser.parseFromString(html, "text/html");

    // 서버 응답의 HTML 구조를 확인하고 실제로 상품 목록이 들어있는 부분을 지정
    const productsContainer = doc.querySelector(".row");
    return productsContainer ? productsContainer.innerHTML : '';
}

// 페이징 버튼들을 동적으로 생성하는 함수
function initializePagination(data) {
    const paginationContainer = document.querySelector(".pagination");

    // 기존 페이징 버튼들을 삭제
    paginationContainer.innerHTML = '';

    // 검색 결과에 따라 페이징 버튼을 동적으로 생성
    const totalPages = data.totalPages;
    const currentPage = data.number;

    for (let i = 0; i < totalPages; i++) {
        const pageButton = document.createElement("a");
        pageButton.href = "#";
        pageButton.setAttribute("data-page", i);
        pageButton.textContent = i + 1;

        if (i === currentPage) {
            pageButton.classList.add("current-page");
        }

        // 클릭 이벤트 핸들러 설정
        pageButton.addEventListener("click", function (event) {
            event.preventDefault();
            const page = this.getAttribute("data-page");
            performSearch(page);
        });

        paginationContainer.appendChild(pageButton);
    }
}
