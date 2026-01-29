# 06-Espera_wait

## 1.Per què s’atura l’execució al cap d’un temps?
Perquè si placesdisponibles és igual a 0, i un assistent vol reservar es dorm amb wait(), espera indefinidament sense executar cap mena de codi, ni imprimeix res ni consumeix CPU, es queda esperant fins algun altre fil faci un notifyAll()

---
## 2.Què passaria si en lloc de una probalitat de 50%-50% fora de 70%(ferReserva)-30%(cancel·lar)? I si foren al revés les probalitats? -> Mostra la porció de codi modificada i la sortida resultant en cada un dels 2 casos.
Hi haurà més probalitat de que els fils entrin en wait() i així es bloquegin perquè les places s'omplen més ràpidament.
En cas del contrari hi haurà mes interracions ya que s'omple les places més lent ya que hi ha menys probalitat de reserves i sovint hi haurà cancel·lacio de reserves inexistents.

---
## 3.Perquè creus que fa falta la llista i no valdria només amb una variable sencera de reserves?
És necessària perquè comprovem que l'assistent té una reserva si utilizem un int, no sabem qui té reserva o no. Perquè amb el int només tindríem  un comptador