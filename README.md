# Домашнее задание
## Основы автоматизированного тестирования

В заданиии были написаны тесты на класс `Rational`. В ходе тестирования обнаружились проблемы -
* Численные переполнения в арифметических операциях
* Некорректная работа метода `less`

Для устранения проблемы переполнений было принятно решение все внутренние вычисления проводить в типе `long`, при этом конструктор класса оставить без изменений и от клиента принимать тип `int`.

Для исправления метода `less` вызов `getNumerator()` был заменен на `rational.getNumerator()`
