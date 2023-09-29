
function generateIDs(){
    const counters = document.querySelectorAll('.counter');
    counters.forEach((counter, index) => {
    counter.id = `inCart${index + 1}`;
    });
};
function changeCart(button, action){
    let tr = button.parentNode.parentNode;
    let td = tr.querySelector(".counter")
    let clicks = parseInt(td.value)

    if(action === "increment")
        clicks += 1;
    else if(action === "decrement" && clicks > 0)
        clicks -= 1;
    td.value = clicks.toString();

}

// function onLoadToggleVisibility(){
//     let toggleVisibilityButtons = document.getElementsByClassName("toggleButton");
//     console.log(toggleVisibilityButtons)
//     let toggleVisibilitySpan = document.getElementById("toggleSpan");
//     let isItemVisible = false;

    function toggleItemVisibility(button) {
        let toggleSpan = button.parentNode.querySelector(".toggleSpan")
        if (toggleSpan.style.display === "block") {
            toggleSpan.style.display = "none"
        } else {
            toggleSpan.style.display = "block";
        }
    }

