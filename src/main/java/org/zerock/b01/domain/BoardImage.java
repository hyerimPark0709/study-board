package org.zerock.b01.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
// 첨부파일을 의미하는 class
public class BoardImage implements Comparable<BoardImage> {

    @Id
    private String uuid; // 주소
    private String fileName;
    private int ord; // 파일 순서

    @ManyToOne
    private Board board;

    @Override
    public int compareTo(BoardImage other) {
        return this.ord = ord;
    }

    public void changeBoard(Board board) {
        this.board = board;
    }
}
