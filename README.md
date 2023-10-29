# Repozitorij za laboratorijske vježbe iz kolegija Otvoreno računarstvo
U ovom repozitoriju nalaze se rješenja laboratorijskih vježbi iz predmeta Otvoreno računarstvo.
# Otvoreni skup podataka - dodjela nagrade Oscar
Baza podataka sadržana u ovom repozitoriju sadrži informacije o dodjelama nagrada Oscar za glumu  na ceremonijama 2004.,2005. i 2007. godine.
Podaci sadržani u bazi su uzeti sa stranice wikipedia.org gdje su podaci licencirani otvorenom licencom "Creative Commons Attribution-ShareAlike 4.0 International License".

|O skupu podataka: | |
|---- | ----|
|Licenca: | Creative Commons Attribution-ShareAlike 4.0 International License|
|Verzija podataka: |Release 1.0.|
|Autor: |Tomislav Grudić|
|Jezik: | hrvatski|

|Atribut:| opis| tip podatka |
|---|----|----|
|naziv_ceremonije| naziv ceremonije Oscar | varchar() |
|datum_ceremonije| datum ceremonije Oscar |  date |
|lokacija_ceremonije| mjesto održavanja ceremonije Oscar | varchar() |
|kategorija_nagrade| kategorija može biti: Najbolji glumac, Njabolji sporedni glumac, Najbolja glumica, Najbolja sporedna glumica | varchar() |
|dobitnik_ime| ime dobitnika nagrade Oscar | varchar() |
|dobitnik_prezime| prezime dobitnika nagrade Oscar | varchar() |
|dobitnik_datumrod|  datum rođenja dobitnika nagrade Oscar| date |
|film|ime filma za koji je dobitnik nagrađen| varchar() |
|ime_lika_u_filmu| ime lika u filmu kojeg je dobitnik nagrade portretirao | varchar() |
|redatelj| redatelj filma za koji je dobitnik nagrađen | varchar() |

# Odnos i povezanost atributa
Odnos roditelj-dijete : ceremonija (naziv_ceremonije,datum_ceremonije,lokacija_ceremonije) ima više dodijeljenih nagrada Oscar, a svaka nagrada sadrži podatak (kategorija_nagrade,dobitnik_ime, dobitnik_prezime,dobitnik_datumrod,film,ime_lika_u_filmu,redatelj)
