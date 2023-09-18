
function generateIDs(){
    const counters = document.querySelectorAll('.Counter');
    counters.forEach((counter, index) => {
    counter.id = `inCart${index + 1}`;
    });
};
function changeCart(button, action){
    let tr = button.parentNode.parentNode;
    let td = tr.querySelector(".Counter")
    let clicks = parseInt(td.innerText)

    if(action === "increment")
        clicks += 1;
    else if(action === "decrement" && clicks > 0)
        clicks -= 1;
    td.innerText = clicks.toString();

};