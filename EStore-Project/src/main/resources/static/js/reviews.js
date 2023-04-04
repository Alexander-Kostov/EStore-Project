const backendLocation = 'http://localhost:8080'

let productId = document.getElementById("productId").getAttribute("value")
let reviewSection = document.getElementById("reviews-section")
fetch(`${backendLocation}/products-details/${productId}/reviews`)
.then((response) => response.json())
.then((body) => {
    for (review of body) {
        addReviewAsHtml(review)
    }
})

function addReviewAsHtml(review) {
    let reviewHtml = `<div class="reviewer" id="review${review.id}">\n` + review.username + ` - `
    reviewHtml += `<span>` + review.dateOfCreation + `</span>\n`
    reviewHtml += `</div>\n`
    reviewHtml += `<div class="ratting">`
    reviewHtml += `<i id="star" class="fa fa-star">` + `</i>`
    reviewHtml += `<i id="star" class="fa fa-star">` + `</i>`
    reviewHtml += `<i id="star" class="fa fa-star">` + `</i>`
    reviewHtml += `<i id="star" class="fa fa-star">` + `</i>`
    reviewHtml += `<i id="star" class="fa fa-star">` + `</i>`
    reviewHtml += `<button id="delete-review-button" class="btn btn-danger" onclick="deleteReview(${review.id})">Delete</button>\n`
    reviewHtml += `</div>\n`
    reviewHtml += `<p>` + review.text + `</p\n>`
    reviewHtml += `<div hidden>` + review.id + `</div>`

    reviewSection.innerHTML += reviewHtml
}

function deleteReview(reviewId) {
    fetch(`${backendLocation}/products-details/${productId}/reviews/${reviewId}`, {
        method: 'DELETE',
        headers: {
            [csrfHeaderName]: csrfHeaderValue
        }
    }).then(res => {
        console.log(res)
        document.getElementById("review" + reviewId).remove()
    })
}

// function deleteReview(reviewId) {
//     fetch(`${backendLocation}/products-details/${productId}/reviews/${reviewId}`, {
//         method: 'DELETE',
//         headers: {
//             [csrfHeaderName]: csrfHeaderValue
//         }
//     }).then(res => {
//         console.log(res)
//         document.getElementById("review" + reviewId).remove()
//     })
// }



const csrfHeaderName = document.getElementById('csrf').getAttribute('name')
const csrfHeaderValue = document.getElementById('csrf').getAttribute('value')

let reviewForm = document.getElementById("review-form")
reviewForm.addEventListener("submit", (event) => {
    event.preventDefault()

    let username = document.getElementById("username").value
    let text = document.getElementById("text-area").value

    fetch(`${backendLocation}/products-details/${productId}/reviews`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            [csrfHeaderName]: csrfHeaderValue
        },
        body: JSON.stringify({
            text: text,
            username: username
        })
    }).then((res) => {
        document.getElementById("username").value = ""
        document.getElementById("text-area").value = ""
        let location = res.headers.get("Location")
        fetch(`${backendLocation}${location}`)
            .then(res => res.json())
            .then(body => addReviewAsHtml(body))
    })
})