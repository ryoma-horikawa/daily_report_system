package services;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;

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
        long clients_count = (long) em.createNamedQuery(JpaConst.Q_CLI_COUNT, Long.class)
                .getSingleResult();
        return clients_count;
    }

    /**
     * 社員番号条件に取得したデータをClienteViewのインスタンスで返却する
     * @param code 社員番号
     * @return 取得データのインスタンス 取得できない場合null
     */
    public ClientView findOne(String cli_code) {
        Client c = null;
        try {


            //顧客番号を条件に顧客情報を1件取得する
            c = em.createNamedQuery(JpaConst.Q_CLI_COUNT_REGISTERED_BY_CLICODE, Client.class)
                    .setParameter(JpaConst.JPQL_PARM_CLICODE, cli_code)
                    .getSingleResult();

        } catch (NoResultException ex) {
        }

        return ClientConverter.toView(c);

    }




    /**
     * idを条件に取得したデータをReportViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public ClientView findOne(int id) {
        return ClientConverter.toView(findOneInternal(id));
    }

    /**
     * 顧客番号を条件に該当するデータの件数を取得し、返却する
     * @param cli_code 顧客番号
     * @return 該当するデータの件数
     */
    public long countByCli_code(String cli_code) {
      //指定した社員番号を保持する従業員の件数を取得する
      long client_count = (long) em.createNamedQuery(JpaConst.Q_CLI_COUNT_REGISTERED_BY_CLICODE, Long.class)
            .setParameter(JpaConst.JPQL_PARM_CLICODE, cli_code)
            .getSingleResult();
      return client_count;
    }

    /**
     * 画面から入力された顧客の登録内容を元にデータを1件作成し、顧客テーブルに登録する
     * @param cv 顧客の登録内容
     * @return バリデーションで発生したエラーのリスト
     */


      public List<String> create(ClientView cv) {
          List<String> errors = ClientValidator.validate(this, cv, true);
          if (errors.size() == 0) {
              LocalDateTime ldt = LocalDateTime.now();
              cv.setCreatedAt(ldt);
              cv.setUpdatedAt(ldt);
              createInternal(cv);
           }



            //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
            return errors;
        }

        //登録日時、更新日時は現在時刻に設定する
        /*
        public List<String> create(ClientView cv)
        LocalDateTime now = LocalDateTime.now();
        cv.setCreatedAt(now);
        cv.setUpdatedAt(now);

        List<String> errors = ClientValidator.validate(this, cv, true);

        //バリデーションエラーがなければデータを登録する
        if (errors.size() == 0) {
            create(cv);
        }

         //エラーを返却（エラーがなければ0件の空リスト）
        return errors;


    }
    */
    //上記、以前の記述
    /*
     public List<String> create(ClientView cv){
         List<String> errors = ClientValidator.validate();
         if (errors.size() == 0) {
             LocalDateTime ldt = LocalDateTime.now();
             cv.setCreatedAt(ldt);
             cv.setUpdatedAt(ldt);
             createInternal(cv);
         }

       //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
         return errors;
     }
     */

     /**
      * 画面から入力された顧客の登録内容を元に、顧客データを更新する
      * @param cv 顧客の更新内容
      * @return バリデーションで発生したエラーのリスト
      */
      public List<String> update(ClientView cv){

          //idを条件に登録済みの顧客情報を取得する
          ClientView savedCli = findOne(cv.getId());

          savedCli.setCli_name(cv.getCli_name()); //変更後の会社名を設定する

          //更新日時に現在時刻を設定する
          LocalDateTime ldt = LocalDateTime.now();
          cv.setUpdatedAt(ldt);

          updateInternal(cv);
          /*
          LocalDateTime today = LocalDateTime.now();
          savedCli.setUpdatedAt(today);
          */
          //更新内容についてバリデーションを行う
          List<String> errors = ClientValidator.validate(this, cv, true);

          //バリデーションエラーがなければデータを更新する
          if (errors.size() == 0) {
              update(savedCli);
          }

          //エラーを返却（エラーがなければ0件の空リスト
          return errors;


      }


    /*
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
*/
     /**
      * idを条件にデータを1件取得する
      * @param id
      * @return 取得データのインスタンス
      */

      private Client findOneInternal(int id) {
          Client c = em.find(Client.class, id);

          return c;

      }
      /*
     private Client findOneInternal(int id) {
         return em.find(Client.class, id);


     }
     */

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





