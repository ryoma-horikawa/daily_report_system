package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.ClientConverter;
import actions.views.ClientView;
import constants.JpaConst;
import models.Client;
import models.validators.ClientValidator;

/**
 * 顧客テーブルの操作に関わる処理を行うクラス
 *
 */
public class ClientService extends ServiceBase {


    /**
     * 指定されたページ数の一覧画面に表示する日報データを取得し、ReportViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<ClientView> getAllPerPage(int page){

        List<Client> clients = em.createNamedQuery(JpaConst.Q_CLI_GET_ALL,Client.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return ClientConverter.toViewList(clients);
    }

    /**
     * 顧客テーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long clients_count = (long) em.createNamedQuery(JpaConst.Q_REP_COUNT, Long.class)
                .getSingleResult();
        return clients_count;
    }

    /**
     * 画面から入力された顧客の登録内容を元にデータを1件作成し、顧客テーブルに登録する
     * @param cv 顧客の登録内容
     * @return バリデーションで発生したエラーのリスト
     */
     public List<String> create(ClientView cv){
         List<String> errors = ClientValidator.validate(cv);
         if (errors.size() == 0) {
             LocalDateTime ldt = LocalDateTime.now();
             cv.setCreatedAt(ldt);
             cv.setUpdatedAt(ldt);
             createInternal(cv);
         }

       //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
         return errors;
     }

     /**
      * 画面から入力された顧客の登録内容を元に、顧客データを更新する
      * @param cv 顧客の更新内容
      * @return バリデーションで発生したエラーのリスト
      */
     public List<String> update(ClientView cv){

         //バリデーションを行う
         List<String> errors = ClientValidator.validate(cv);

         if (errors.size() == 0) {

             //更新日時を現在時刻に設定
             LocalDateTime ldt = LocalDateTime.now();
             cv.setUpdatedAt(ldt);

             updateInternal(cv);
         }

       //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
         return errors;
     }

     /**
      * idを条件にデータを1件取得する
      * @param id
      * @return 取得データのインスタンス
      */
     private Client findOneInternal(int id) {
         return em.find(Client.class, id);
     }

     /**
      * 日報データを1件登録する
      * @param rv 日報データ
      */
     private void createInternal(ClientView cv) {

         em.getTransaction().begin();
         em.persist(ClientConverter.toModel(cv));
         em.getTransaction().commit();
     }

     /**
      * 顧客データを更新する
      * @param cv 顧客データ
      */
     private void updateInternal(ClientView cv) {

         em.getTransaction().begin();
         Client c = findOneInternal(cv.getId());
         ClientConverter.copyViewToModel(c, cv);
         em.getTransaction().commit();
     }





}






















