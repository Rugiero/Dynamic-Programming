Ohjelma on rekursiivinen algoritmi, mikä yrittää mahduttaa laatikon aina uuteen tilaan, mikä jää jäljelle edellisen laatikon sijoittamisesta. Algoritmin perusperiaate on:

Vaihe1: Sijoita laatikko, millä on suurin tilavuus. Sijoitetaan laatikko niin, että sen korkeus on sen lyhyin sivu.
Vaihe2: Sijoitetaan edellisen laatikon viereen laatikko, millä on suurin pinta-ala. Jos mahtuvia laatikoita on monia, sijoitetaan laatikko jolla on pienin korkeus.
Vaihe2.2: Jatketaan rekursiiviseti kunnes laatikoita ei mahdu. Joka laatikon sijoittamisen jälkeen vapautuu kaksi uutta tilaa, mihin voidaan sijoittaa uusi laatikko. Docs- kansiosta löytyy kuva kuvaamaan rekursiota.

Suurimittainen tilavaatimus:
2 kpl n- olioista listaa. Rekursiivinen algoritmi vie javan pinoa pahimmillaan 4n²(hieman epävarma).

Aikavaatimus:
Listojen järjestäminen O(n²), algoritmi O(n²). Siis kompleksisuus O(n²)




Työ perustuu seuraavaan kuvaukseen algoritmista:

http://www.zahidgurbuz.com/yayinlar/An%20Efficient%20Algorithm%20for%203D%20Rectangular%20Box%20Packing.pdf
