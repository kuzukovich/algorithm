package sky.pro;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private String[] array;
    private int index = 0;
    int size = 8;


    public StringListImpl() {
        array = new String[size];
    }

    // Добавление элемента.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    @Override
    public String add(String item) {
        checkNotNull(item);
        checkEnoughSize(index);
        item = array[index++];
        return item;
    }


    // Добавление элемента
    // на определенную позицию списка.
    // Если выходит за пределы фактического
    // количества элементов или массива,
    // выбросить исключение.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    @Override
    public String add(int index, String item) {
        checkNotNull(item);
        checkIndex(index);
        checkEnoughSize(index);
        System.arraycopy(array, index, array, index + 1, array.length - index);
        item = array[index++];
        return item;
    }

    // Установить элемент
    // на определенную позицию,
    // затерев существующий.
    // Выбросить исключение,
    // если индекс меньше
    // фактического количества элементов
    // или выходит за пределы массива.
    @Override
    public String set(int index, String item) {
        checkNotNull(item);
        checkIndex(index);
        item = array[index];
        return item;
    }

    // Удаление элемента.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public String remove(String item) {
        checkNotNull(item);
        checkElementExist(item);
        int index = indexOf(item);
        removeElement(index);
        return item;
    }

    public void removeElement(int index) {
        if (size - 1 > index) {
            System.arraycopy(array, index + 1, array, index, array.length - index);
            array[--this.index] = null;
        }
    }

    // Удаление элемента по индексу.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public String remove(int index) {
        checkIndex(index);
        String item = get(index);
        removeElement(index);
        return item;
    }

    // Проверка на существование элемента.
    // Вернуть true/false;
    @Override
    public boolean contains(String item) {
        checkNotNull(item);
        boolean result = false;
        for (int i = 0; i < index; i++) {
            if (array[i].equals(item)) {
                result = true;
                break;
            }
        }
        return result;
    }

    // Поиск элемента.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int indexOf(String item) {
        int index = -1;
        checkNotNull(item);
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    // Поиск элемента с конца.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int lastIndexOf(String item) {
        int index = -1;
        checkNotNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    // Получить элемент по индексу.
    // Вернуть элемент или исключение,
    // если выходит за рамки фактического
    // количества элементов.
    @Override
    public String get(int index) {
        checkIndex(index);
        return array[index];
    }

    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение,
    // если передан null.

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            return false;
        }
        if (size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    // Вернуть фактическое количество элементов.
    public int size() {
        return index;
    }

    // Вернуть true,
    // если элементов в списке нет,
    // иначе false.
    public boolean isEmpty() {
        return index == 0;
    }

    // Удалить все элементы из списка.
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
    }

    // Создать новый массив
    // из строк в списке
    // и вернуть его.
    @Override
    public String[] toArray() {
        return Arrays.copyOf(array, index);
    }


    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is not valid");
        }
    }

    private void checkNotNull(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Value is not filled");
        }
    }

    private void checkElementExist(String item) {
        if (indexOf(item) == -1) {
            throw new IllegalArgumentException("The element does not exist in the list");
        }
    }

    private void checkEnoughSize(int index) {
        if (index == array.length - 1) {
            resizeArray(array.length * 2);
        }
    }

    private void resizeArray(int newSize) {
        array = Arrays.copyOf(array, newSize);
    }

}