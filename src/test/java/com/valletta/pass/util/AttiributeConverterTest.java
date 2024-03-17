package com.valletta.pass.util;

import static org.hamcrest.Matchers.is;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AttiributeConverterTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    @Transactional
    public void Attiribute_Converter() {
        Member member = new Member();
//        member.setId(1);
        member.setGender("남자");
        member.setUpdatedAt(new Date());
        member.setCreatedAt(LocalDateTime.now());

        em.persist(member);
//        em.flush();
//        em.clear();

        // native query를 이용하여 gender = 1 조회
//        Query query = em.createNativeQuery("select * from member_converter where gender = :gender", Member.class);
//        query.setParameter("gender", 1);
//        List<Member> list = query.getResultList();
//
//        // 검증
//        String resultGender = list.get(0).getGender();
//        Assert.assertThat("남자", is(resultGender));
    }
}

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "member_converter")
@Entity
class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Convert(converter = GenderAttributeConverter.class)
    private String gender;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;
}

@Converter
class GenderAttributeConverter implements AttributeConverter<String, Integer> {

    @Override
    public Integer convertToDatabaseColumn(String s) {
        if ("남자".equals(s)) {
            return 1;
        } else if ("여자".equals(s)) {
            return 2;
        }
        return 0;
    }

    @Override
    public String convertToEntityAttribute(Integer code) {
        if (code == 1) {
            return "남자";
        } else if (code == 2) {
            return "여자";
        }
        return "중성";
    }
}
