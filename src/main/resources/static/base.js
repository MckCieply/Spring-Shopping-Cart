let clicks = 0
function generateClicks(){
    let length = document.getElementById("lengthOfStock").value;
    let tabOfClicks = new Array(length);
    return tabOfClicks;
}
function addToCart(){
    clicks += 1;
    document.getElementById("inCart").innerHTML = clicks;

}
function removeFromCart(){
    clicks -= 1;
    document.getElementById("inCart").innerHTML = clicks;

}