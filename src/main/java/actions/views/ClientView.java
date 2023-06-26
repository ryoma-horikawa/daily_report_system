package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 顧客情報について画面の入力値・出力値を扱うViewモデル
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientView {

    /**
     * id
     */
    private Integer id;

    /**
     * 顧客情報を登録した従業員
     */
    private EmployeeView employee;

    /**
     * 顧客の名前
     */
    private String name;

    /**
     * 顧客の情報
     */
    private String information;

    /**
     * 顧客の住所
     */
    private String address;

    /**
     * 顧客の電話番号
     */
    private String phone;

    /**
     * 顧客のメールアドレス
     */
    private String email;

    /**
     * 顧客の担当者
     */
    private String customer;

    /**
     * 社内の担当
     */
    private String manager;

    /**
     * 登録日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    private LocalDateTime updatedAt;
}



