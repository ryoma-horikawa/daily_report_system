package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 顧客データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_CLI)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_CLI_GET_ALL,
            query = JpaConst.Q_CLI_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_CLI_COUNT,
            query = JpaConst.Q_CLI_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_CLI_COUNT_REGISTERED_BY_CLICODE,
            query = JpaConst.Q_CLI_COUNT_REGISTERED_BY_CLICODE_DEF)


})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Client {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.CLI_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 顧客情報を登録した従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.CLI_COL_EMP, nullable = false)
    private Employee employee;

    /**
     * 顧客番号
     */
    @Column(name = JpaConst.CLI_COL_CLICODE, length = 5 ,nullable = false, unique = true)
    private String cli_code;

    /**
     * 顧客の名前
     */
    @Column(name = JpaConst.CLI_COL_CLINAME, nullable = false)
    private String cli_name;

    /**
     * 顧客の情報
     */
    @Column(name = JpaConst.CLI_COL_INFO)
    private String information;

    /**
     * 顧客の住所
     */
    @Column(name = JpaConst.CLI_COL_ADDRESS)
    private String address;

    /**
     * 顧客の電話番号
     */
    @Column(name = JpaConst.CLI_COL_PHONE)
    private String phone;

    /**
     * 顧客のメールアドレス
     */
    @Column(name = JpaConst.CLI_COL_EMAIL)
    private String email;

    /**
     * 顧客の担当者
     */
    @Column(name = JpaConst.CLI_COL_CUSTOMER)
    private String customer;

    /**
     * 社内の担当者
     */
    @Column(name = JpaConst.CLI_COL_MANAGER)
    private String manager;

    /**
     * 登録日時
     */
    @Column(name = JpaConst.CLI_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.CLI_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

}
