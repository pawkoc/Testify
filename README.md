## Spis treści

- [Cel projektu](#cel-projektu)
- [Stos technologiczny](#stos-technologiczny)
- [Techniki komponentowe](#techniki-komponentowe)
- [Architektura](#architektura)

### Cel projektu

Celem projektu Testify jest stworzenie platformy, która umożliwi udostępnianie szkieletów zadań, pobieranie ich oraz dodawania rozwiązań do wspomnianych zadań. Po wrzuceniu zadania zostanie ono zbudowane odpowiednio przygotowanym skryptem oraz uruchomione z zestawem testów. Na podstawie wyników tych testów użytkownik uzyska informację zwrotną na temat poprawności wykonania zadania. Dodatkowo będzie możliwość stworzenia rankingów najlepszych zadań.

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

### Techniki komponentowe
- AOP - aspekty do np. logowania informacji
- Spring MVC - podstawowy framework, który zostanie użyty. W ramach projektu ma powstać także aplikacja webowa, służąca jako interfejs dla użytkownika.

### Architektura

Cały system będzie składał się z dwóch modułów. Jeden będzie odpowiedzialny za komunikację z użytkownikiem oraz dostarczanie mu wszytskich podstawowych funkcjonalności (jak np. logowanie się do systemu itp.) oraz z modułu, który będzie przygotowywał środowisko i uruchamiał w nim załadowane przez użytkownika zadanie. Następnie będzie on zwracał wyniki do pierwszego modułu, który będzie je interpretował oraz przetwarzał w celu przedtstawienia ich użytkownikom. Drugi moduł będzie mógł być uruchomiony w wielu instancjach co pozwoli na równoległe wykonywanie wielu zadań.
