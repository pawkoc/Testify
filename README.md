## Spis treści

- [Cel projektu](#cel-projektu)
- [Stos technologiczny](#stos-technologiczny)
- [Architektura](#architektura)
- [Techniki komponentowe](#techniki-komponentowe)

### Cel projektu

Celem projektu Testify jest stworzenie platformy, która umożliwi udostępnianie szkieletów zadań, pobieranie ich oraz dodawania rozwiązań do wspomnianych zadań. Po wrzuceniu zadania zostanie ono zbudowane odpowiednio przygotowanym skryptem oraz uruchomione z zestawem testów. Na podstawie wyników tych testów użytkownik uzyska informację zwrotną na temat poprawności wykonania zadania. Dodatkowo system będzie oferował możliwość stworzenia rankingów najlepszych zadań.

### Stos technologiczny
- Java 8
- Spring MVC
- Spring Boot
- Spring Cloud
- Spring AOP
- Apache Spark
- Docker
- PostgreSQL
- MongoDB

### Architektura

![Architektura](docs/architecture.png)
*Rys. 1 Architektura*

Rys. 1 prezentuje planowaną architekturę systemu oraz komunikację klientów i poszczególnych komponentów. Wyróżniamy dwa typy użytkowników:
- Prowadzący (Professor) - tworzy zadania i publikuje "szkielety" kodu w systemie, przygotowuje testy, które dostarczone rozwiązanie powinno przejść oraz schemat punktacji,
- Student - pobiera przygotowane przez Prowadzącego zadania. Po ich rozwiązadniu wysyła kod do systemu.

Komunikacja klientów z systemem (logowanie, publikacja/pobranie zadań, wysyłanie rozwiązań, wyświetlenie wyników) odbywa się przy pomocy przeglądarki internetowej.

Część serwerowa składa się z 3 mikroserwisów i Środowiska Wykonawczego (Evaluation server). Serwisy (każdy ma swoją bazę danych zaznaczoną w nawiasie):
- User service (PostgreSQL) - umożliwia logowanie do systemu, przydziela token sesji,
- Assignment Service (MongoDB) - umożliwia dodawanie/pobieranie zadań oraz odbiera rozwiązania od Studenta,
- Result Service (PostgreSQL) - umożliwia pobieranie informacji o wynikach za zadania dla poszczególnych Studentów.

Środowisko Wykonawcze to komponent odpowiedzialny za uruchamianie i ocenę rozwiązań (w postaci plików .jar) nadesłanych przez Studentów. Na nadesłanym kodzie wykonuje szereg operacji:
- przepuszczenie przez testy przygotowane przez Prowadzącego,
- pomiar czasu rozwiązania, co umożliwi tworzenie list rankingowych,
- wykrywanie plagiatów w porównaniu do rozwiązań innych Studentów (opcjonalne).

### Techniki komponentowe
- Spring MVC - podstawowy framework, który zostanie użyty. W ramach projektu ma powstać także aplikacja webowa, służąca jako interfejs dla użytkownika,
- AOP - programowanie aspektowe wykorzystamy do logowania informacji oraz do implementacji systemu rozszerzeń - tj. definicji operacji, które Środowisko Wykonawcze wykona na nadesłanym rozwiązaniu. 
