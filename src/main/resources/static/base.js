
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

};