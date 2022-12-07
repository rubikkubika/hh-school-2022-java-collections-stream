package tasks;

import common.Person;
import common.PersonService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
 */

/*
Сложность сортировки O(n * log n)
 */
public class Task1 {

  private final PersonService personService;

  public Task1(PersonService personService) {
    this.personService = personService;
  }

  public List<Person> findOrderedPersons(List<Integer> personIds) {
    Set<Person> persons = personService.findPersons(personIds);
    Map<Integer,Integer> orderMap=
            IntStream.range(0, personIds.size())
                    .boxed()
                    .collect(Collectors.toMap(personIds::get,Function.identity()));
    return persons.stream()
            .sorted(Comparator.comparing(person -> orderMap.get(person.getId())))
            .toList();
  }
}