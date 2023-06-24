// js per la ricerca per salario minimo

let slider = document.getElementById('sliderSalario');
let salaryLabel = document.getElementById('salaryLabel');

let popularJobAdsElement = document.getElementById('mostPopularJobAds');
let newestJobAdsElement = document.getElementById('newestJobAds');

slider.addEventListener('input', function() {
    salaryLabel.innerText = 'Cerca per salario minimo: ' + slider.value;

    let minSalary = slider.value;

    // filtro gli annunci per salario minimo, lo faccio in due loop per poter capire
    // quando non ci sono annunci filtrati in una delle due categorie

    if(popularJobAdsElement !== null) {
        let popularJobAds = document.getElementById('mostPopularJobAds').children;
        let filteredPopularJobAdNumber = popularJobAds.length;
        for (let i = 0; i < popularJobAds.length; i++) {

            let salary = popularJobAds[i].getAttribute('data-salary');

            salary = parseFloat(salary);
            if (salary < minSalary) {
                popularJobAds[i].style.display = 'none';
                filteredPopularJobAdNumber--;
            } else {
                popularJobAds[i].style.display = 'block';
                filteredPopularJobAdNumber++;
            }
        }

        // se non ci sono annunci filtrati, mostro il messaggio di nessun annuncio trovato
        if(filteredPopularJobAdNumber === 0) {
            document.getElementById('noPopularJobAdsFound').style.display = 'block';
        } else {
            document.getElementById('noPopularJobAdsFound').style.display = 'none';
        }
    }

    if(newestJobAdsElement !== null) {
        let newestJobAds = document.getElementById('newestJobAds').children;
        let filteredNewestJobAdNumber = newestJobAds.length;

        for (let i = 0; i < newestJobAds.length; i++) {

            let salary = newestJobAds[i].getAttribute('data-salary');

            salary = parseFloat(salary);
            if (salary < minSalary) {
                newestJobAds[i].style.display = 'none';
                filteredNewestJobAdNumber--;
            } else {
                newestJobAds[i].style.display = 'block';
                filteredNewestJobAdNumber++;
            }
        }
        // se non ci sono annunci filtrati, mostro il messaggio di nessun annuncio trovato
        if(filteredNewestJobAdNumber === 0) {
            document.getElementById('noNewestJobAdsFound').style.display = 'block';
        } else {
            document.getElementById('noNewestJobAdsFound').style.display = 'none';
        }
    }

});

