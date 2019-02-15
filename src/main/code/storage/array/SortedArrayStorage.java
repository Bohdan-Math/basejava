package main.code.storage.array;

import main.code.model.Resume;

import static java.lang.System.arraycopy;
import static java.util.Arrays.binarySearch;
import static java.util.Comparator.comparing;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object getKeyOf(String uuid) {
        Resume resume = new Resume(uuid, "temporally_comparision_name");
        return binarySearch(storage, 0, size, resume, comparing(Resume::getUuid));
    }

    @Override
    protected void insert(Object key, Resume resume) {
        int headLastIndex = -(Integer) key - 1;
        arraycopy(storage, headLastIndex, storage, headLastIndex + 1, size - headLastIndex);
        storage[headLastIndex] = resume;
    }

    @Override
    protected void fillEmptyCell(Object key) {
        int intKey = (Integer) key;
        int emptyCellIndex = intKey + 1;
        arraycopy(storage, emptyCellIndex, storage, intKey, size - emptyCellIndex);
    }
}
