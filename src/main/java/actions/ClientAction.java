package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.ClientView;
import actions.views.EmployeeView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.ClientService;

/**
 * 顧客に関する処理を行うクラス
 *
 */
public class ClientAction extends ActionBase {

    private ClientService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new ClientService();

        //メソッドを実行
        invoke();
        service.close();
    }

    /**
     * 一覧画面を表示
     */
    public void index() throws ServletException, IOException {

        //指定されたページ数の一覧画面に表示する顧客データを取得
        int page = getPage();
        List<ClientView> clients = service.getAllPerPage(page);

        //全顧客データの件数を取得
        long clientsConst = service.countAll();

        putRequestScope(AttributeConst.CLIENTS, clients);
        putRequestScope(AttributeConst.CLI_COUNT, clientsConst);
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);

        //セッションにフラッシュメッセージが設定されている場合
        //リクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_CLI_INDEX);

    }

    /**
     * 新規登録画面を表示する
     */
    public void entryNew() throws ServletException, IOException{

        putRequestScope(AttributeConst.TOKEN, getTokenId());

        //日報の登録画面
        ClientView cv = new ClientView();
        //cv.setRequestDate(LocalDate.now());
        putRequestScope(AttributeConst.CLIENT, cv);

        //新規登録画面を表示
        forward(ForwardConst.FW_CLI_NEW);

    }

    /**
     * 新規登録を行う
     */
    public void create() throws ServletException, IOException{

        if(checkToken()) {

            //セッションからログイン中の従業員情報を取得
            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

            //パラメータの値をもとに顧客情報のインスタンスを作成する
            ClientView cv = new ClientView(
                    null,
                    ev,
                    getRequestParam(AttributeConst.CLI_CLICODE),
                    getRequestParam(AttributeConst.CLI_CLINAME),
                    getRequestParam(AttributeConst.CLI_INFO),
                    getRequestParam(AttributeConst.CLI_ADDRESS),
                    getRequestParam(AttributeConst.CLI_PHONE),
                    getRequestParam(AttributeConst.CLI_EMAIL),
                    getRequestParam(AttributeConst.CLI_CUSTOMER),
                    getRequestParam(AttributeConst.CLI_MANAGER),
                    null,
                    null);

            //顧客情報登録
            List<String> errors = service.create(cv);

            if (errors.size() > 0) {
                //登録中にエラーがあった場合

                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.CLIENT, cv);
                putRequestScope(AttributeConst.ERR, errors);

                //新規登録画面を再表示
                forward(ForwardConst.FW_CLI_NEW);
            } else {
                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_CLI, ForwardConst.CMD_INDEX);
            }
        }
    }

    /**
     * 詳細画面を表示
     */
    public void show() throws ServletException, IOException{
        //idを条件に顧客データを取得する
        ClientView cv = service.findOne(toNumber(getRequestParam(AttributeConst.CLI_ID)));

        if (cv == null) {
            //該当する顧客データが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.CLIENT, cv);

            //詳細画面を表示
            forward(ForwardConst.FW_CLI_SHOW);
        }
    }

    /**
     * 編集画面を表示する
     */
    public void edit() throws ServletException, IOException{

        //idを条件に顧客データを取得する
        ClientView cv = service.findOne(toNumber(getRequestParam(AttributeConst.CLI_ID)));

        //セッションからログイン中の従業員情報を取得
        EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        if (cv == null || ev.getId() != cv.getEmployee().getId()) {
            //該当の顧客データが存在しない、または
            //ログインしている従業員が日報の製作者でない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.TOKEN, getTokenId());
            putRequestScope(AttributeConst.CLIENT, cv);

            //編集画面を表示
            forward(ForwardConst.FW_CLI_EDIT);

        }
    }

    /**
     * 更新を行う
     */
    public void update() throws ServletException, IOException{

        if (checkToken()) {

            //idを条件に顧客データを取得する
            ClientView cv = service.findOne(toNumber(getRequestParam(AttributeConst.CLI_ID)));

            //入力された顧客内容を設定する
            cv.setCli_code(getRequestParam(AttributeConst.CLI_CLICODE));
            cv.setCli_name(getRequestParam(AttributeConst.CLI_CLINAME));
            cv.setInformation(getRequestParam(AttributeConst.CLI_INFO));
            cv.setAddress(getRequestParam(AttributeConst.CLI_ADDRESS));
            cv.setPhone(getRequestParam(AttributeConst.CLI_PHONE));
            cv.setEmail(getRequestParam(AttributeConst.CLI_EMAIL));
            cv.setCustomer(getRequestParam(AttributeConst.CLI_CUSTOMER));
            cv.setManager(getRequestParam(AttributeConst.CLI_MANAGER));

            //顧客データを更新する
            List<String> errors = service.update(cv);

            if (errors.size() > 0) {
                //更新したエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.CLIENT, cv);
                putRequestScope(AttributeConst.ERR, errors);

                //編集画面を再表示
                forward(ForwardConst.FW_CLI_EDIT);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_CLI, ForwardConst.CMD_INDEX);
            }


        }
    }


}

