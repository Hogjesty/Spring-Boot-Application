package ua.example.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class PaginationUtils {

    static List<Integer> getNumbers(Page<?> page) {

        List<Integer> pageNumbers = new ArrayList<>();

        if (page.getTotalPages() > 7) {
            int start;
            int finish;

            if (page.getPageable().getPageNumber() < 4) {
                start = 2;
                finish = page.getTotalPages() - 1;
                if (finish > 6) {
                    finish = 6;
                }
            } else if (page.getPageable().getPageNumber() > page.getTotalPages() - 4) {
                start = page.getTotalPages() - 5;
                finish = page.getTotalPages() - 1;
            } else {
                start = page.getPageable().getPageNumber() - 1;
                finish = page.getTotalPages() - 1;
                if (finish > page.getPageable().getPageNumber() + 3) {
                    finish = page.getPageable().getPageNumber() + 3;
                }
            }

            while (start <= finish) {
                pageNumbers.add(start);
                start++;
            }

        } else {
            pageNumbers = IntStream.rangeClosed(2, page.getTotalPages()-1)
                    .boxed()
                    .collect(Collectors.toList());
        }

        return pageNumbers;
    }

}
