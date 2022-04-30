const log = document.getElementById('log');


document.addEventListener('keypress', logKey);


function logKey(e) {
  console.log("event: ", e)
  log.textContent += ` ${e.code}`;
}