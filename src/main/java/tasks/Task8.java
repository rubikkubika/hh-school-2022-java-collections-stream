package tasks;

import common.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 {
  private long count;
  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй

  /*
  1. Поправил стиль стрима в return
  2. Заменил Collectors to List на toList
  3. Удалил remove, добавил skip  return
  4. У,рал проверку на пустой список, т.к. если на вход даеем пустой список то стрим и вернет пустой список
   */
  public List<String> getNames(List<Person> persons) {

    return persons.stream()
            .skip(1)
            .map(Person::getFirstName)
            .toList();
  }

  //ну и различные имена тоже хочется
     /*
    1. Удалил .distinct() из return,т.к. мы собираем все в Set
    2. Заменил в return стрим на констурктор Hashset
     */
  public Set<String> getDifferentNames(List<Person> persons) {
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
    /*
    1.Заменил второй Second Name на Middle Name
     */
  public String convertPersonToString(Person person) {
    String result = "";
    if (person.getSecondName() != null) {
      result += person.getSecondName();
    }

    if (person.getFirstName() != null) {
      result += " " + person.getFirstName();
    }

    if (person.getMiddleName() != null) {
      result += " " + person.getMiddleName();
    }
    return result;
  }

  // словарь id персоны -> ее имя
    /*
    1. Переписал создание словаря с помощью Stream API
     */
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {

    return persons.stream()
            .collect(Collectors.toMap(Person::getId,Person::getFirstName));
  }

  // есть ли совпадающие в двух коллекциях персоны?
    /*
    1.Переписал проверку с помощью Stream APi, использовал создание СЕТ,чтобы улучшить производительность
     */
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {

    return persons1.stream()
            .anyMatch(new HashSet<>(persons2)::contains);
  }

  //Посчитать четные числа
    /*
    1.Дописал коммент о том, что делает метод
    2.Переписал Stream api с помощью count


     */
  public long countEven(Stream<Integer> numbers) {
    count = numbers
            .filter(num -> num % 2 == 0)
            .count();
    return  count;
  }
}
