package com.pai.app.web.core.framework.web.context;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.pai.app.web.core.constants.WebConstants;
import com.pai.base.api.constants.Constants;
import com.pai.base.db.session.OnlineHolder;
import com.pai.base.db.session.OnlineUserIdHolder;
import com.pai.biz.auth.persistence.entity.AuthUserPo;

/**
 * web层的session
 * <pre> 
 * 构建组：app-web
 * 作者：fuhao
 * 日期：2016年12月29日-上午9:31:13
 * </pre>
 */
public class WebOnlineHolder{
	
	//每一个客户端主线程和session是保持一致的，把session放入线程共享中，方便获取session
//	protected static final ThreadLocal<HttpSession> ouOnlineHolder	= new ThreadLocal<HttpSession>();	
	
	public static void setSession(HttpSession session) {
		OnlineHolder.setSession(session);
		//底层需要用的session
		OnlineUserIdHolder.setUserId(getUserId());
	}
	
	public static String getUserId() {
		if(getUserPo()!=null){
			return getUserPo().getId();
		}
		return null;
	}
	
	public static void setUserPo(HttpSession session,AuthUserPo authUserPo) {
		if(session!=null){
			session.setAttribute(Constants.PAI_AUTH_USER, authUserPo);
			OnlineHolder.setSession(session);
			//数据库插入userId
			OnlineUserIdHolder.setUserId(authUserPo.getId());
		}
	}
	
	public static AuthUserPo getUserPo() {
		if(OnlineHolder.getSession()!=null){
			Object obj = OnlineHolder.getSession().getAttribute(Constants.PAI_AUTH_USER);
			if(obj instanceof AuthUserPo){
				AuthUserPo userPo = (AuthUserPo)obj;
				return userPo;
			}			
		}
		return null;
	}
	
	public static void setAuthResUrl(List<String> urls) {
		if(OnlineHolder.getSession()!=null){
			OnlineHolder.getSession().setAttribute(Constants.PAI_AUTH_RES_URL, urls);
		}
	}
	
	public static List<String> getAuthResUrls(){
		if(OnlineHolder.getSession()!=null){
			return (List<String>) OnlineHolder.getSession().getAttribute(Constants.PAI_AUTH_RES_URL);
		}
		return null;
	}
	/*
	public static void logout() {
		//抛出登出事件，进行扩展业务处理
		if(getUserPo()!=null){
			UserLogoutEvent userLogoutEvent = new UserLogoutEvent(getUserPo());
			SpringHelper.publishEvent(userLogoutEvent);
			ouOnlineHolder.get().setAttribute(WebConstants.ONLINE_OU, null);
			ouOnlineHolder.remove();
		}								
	}
	
	*/
	
	public  static HttpSession getSession(){
		return OnlineHolder.getSession();
	}

	public static String getSid(){
		if(OnlineHolder.getSession()!=null){
			Object sid = OnlineHolder.getSession().getAttribute(WebConstants.ONLINE_MEMBER);
			if(sid!=null && sid instanceof String){
				return (String)sid;
			}
		}
		return null;
	}
	
	/**
	 * api session随机id
	 * @param sid
	 */
	public static void setSid(String sid){
		if(OnlineHolder.getSession()!=null){
			OnlineHolder.getSession().setAttribute(WebConstants.ONLINE_MEMBER,sid);
		}
	}
	
	/*public static void setMemberView(HttpSession session,String sid, MemberView memberView) {
		if(ouOnlineHolder.get()==null){
			session.setAttribute(WebConstants.ONLINE_MEMBER, sid);
			session.setAttribute(sid, memberView);
			ouOnlineHolder.set(session);
		}else{
			ouOnlineHolder.get().setAttribute(WebConstants.ONLINE_MEMBER, sid);
			ouOnlineHolder.get().setAttribute(sid, memberView);
		}
	}*/	
	
	
	
	
	/*public static void setWapPerLoginUrl(HttpSession session,String perLoginUrl ) {
		if(ouOnlineHolder.get()==null){
			setSession(session);
		}
		ouOnlineHolder.get().setAttribute(WebConstants.PRE_LOGIN_URL,perLoginUrl);
	}	*/
	
	/*public static String getWapPerLoginUrl( ) {
		if(OnlineHolder.getSession()!=null){
			return (String) OnlineHolder.getSession().getAttribute(WebConstants.PRE_LOGIN_URL);
		}
		return "";
	}*/	
	
	
	
	/*public static MemberView getMemberView() {
		if(ouOnlineHolder.get()!=null){
			String sid = getSid();
			if(sid !=null){
				Object obj = ouOnlineHolder.get().getAttribute((String)sid);
				if(obj instanceof MemberView){
					MemberView memberView = (MemberView)obj;
					return memberView;
				}		
			}
		}
		return null;
	}
	public static MemberView getMemberView(String sid) {
		if (StringUtils.isNotEmpty(sid)&&ouOnlineHolder.get()!=null) {
			Object obj = ouOnlineHolder.get().getAttribute(sid);
			if(obj instanceof MemberView){
				MemberView memberView = (MemberView)obj;
				return memberView;
			}	
		}else{
			return getMemberView();
		}
		return null;
	}*/
	public static void setDirectLogin(HttpSession session,String direct){
		if(OnlineHolder.getSession()==null){
			OnlineHolder.setSession(session);
		}
		OnlineHolder.getSession().setAttribute(WebConstants.DIRECT_LOGIN,direct);
	}

	/*public static void setCheckoutFlag(String checkFlag) {
		if(ouOnlineHolder.get()!=null){
			ouOnlineHolder.get().setAttribute(WebConstants.CHECK_FLAG,checkFlag);
		}
	}*/
	
	/*public static String getCheckoutFlag() {
		String checkFlag = null;
		if(ouOnlineHolder.get()!=null){
			checkFlag = (String)ouOnlineHolder.get().getAttribute(WebConstants.CHECK_FLAG);			
		}
		return checkFlag;	
	}*/
	
	/**
	 * 判断前端会员是否登录
	 * @return
	 */
	/*public static boolean isLogin(){
		if(getMemberView()==null){
			return false;
		}
		return true;
	}*/
	
	/**
	 * 判断前端会员是否未登录
	 * @return
	 */
	/*public static boolean isNotLogin(){
		return !isLogin();
	}*/
	
	/**
	 * 设置游客ID 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	/*public static void setVid(String vid){
		if(ouOnlineHolder.get()!=null){
			ouOnlineHolder.get().setAttribute(WebConstants.VISITOR_ID,vid);
		}
	}
	public static String getVid(){
		if(ouOnlineHolder.get()!=null){
			Object obj = ouOnlineHolder.get().getAttribute(WebConstants.VISITOR_ID);
			if(obj!=null){
				return (String)obj;
			}
		}
		return "";
	}*/
	/*public static void setSource(String source){
		if(ouOnlineHolder.get()!=null){
			ouOnlineHolder.get().setAttribute("source", source);
		}
	}
	public static String getSource(){
		if(ouOnlineHolder.get()!=null){
			return (String)ouOnlineHolder.get().getAttribute("source");
		}
		return "";
	}*/
	/*public static void setWapSource(String source){
		if(ouOnlineHolder.get()!=null){
			ouOnlineHolder.get().setAttribute("wapsource", source);
		}
	}
	public static String getWapSource(){
		if(ouOnlineHolder.get()!=null){
			return (String)ouOnlineHolder.get().getAttribute("wapsource");
		}
		return "";
	}*/	
	
	
	/*public static void setFromTrackPoSession(HttpSession session,FromTrackPo fromTrackPo){
		ouOnlineHolder.set(session);
		if(ouOnlineHolder.get()!=null){
			ouOnlineHolder.get().setAttribute(WebConstants.FROM_TRACK,fromTrackPo);
		}
	}
	
	public static FromTrackPo getFromTrackPoSession(){
		if(ouOnlineHolder.get()!=null){
			FromTrackPo fromTrackPo = (FromTrackPo)ouOnlineHolder.get().getAttribute(WebConstants.FROM_TRACK);	
			return fromTrackPo;
		}
		return null;
	}
	*//**
	 * 根据cartId获得购物车视图
	 * @return
	 *//*
	public static CartEntityView getCartEntityView(String _cartId) {
		if(StringUtils.isEmpty(_cartId))
			return null;
		return JedisUtil.getObject(_cartId,RedisConstants.CART);
	}*/
	/*public static String getIsReMeLogin(){
		if(ouOnlineHolder.get()!=null){
			String isReMeLogin=(String) ouOnlineHolder.get().getAttribute(WebConstants.IS_RE_ME_LOGIN);
			return isReMeLogin;
		}
		return null;
	}
	public static void setIsReMeLogin(HttpSession session,String isReMeLogin){
		ouOnlineHolder.set(session);
		if(ouOnlineHolder.get()!=null){
			ouOnlineHolder.get().setAttribute(WebConstants.IS_RE_ME_LOGIN, isReMeLogin);
		}
	}*/
	
	/**
	 * 判断时间戳间隔是否大于某个值
	 * @param lastTime
	 * @param intervalTime
	 * @return
	 */
	/*public static boolean judgeTimeInterval(Long lastTime, Long intervalTime){
		Calendar c = Calendar.getInstance();
		long now = c.getTimeInMillis();
		return (now - lastTime) >= intervalTime;
		
	}*/
	/**
	 * 
	 * @param bbsMemberInfoView
	 */
	/*public static  void  setBbsMemberInfoAPIResult(BbsMemberInfoAPIResult bbsMemberInfoAPIResult){
		if(ouOnlineHolder.get()!=null){
			Map<String, BbsMemberInfoAPIResult> map = (Map<String, BbsMemberInfoAPIResult>) ouOnlineHolder.get().getAttribute(WebConstants.BBS_MEMBERINFO_API_RESULT);
			if(map == null){
				map=new HashMap<String, BbsMemberInfoAPIResult> ();
			}
			if(null!=bbsMemberInfoAPIResult && null!=bbsMemberInfoAPIResult.getBbsMemberInfoView()){
				map.put(bbsMemberInfoAPIResult.getBbsMemberInfoView().getMemberId(), bbsMemberInfoAPIResult);
			}
			ouOnlineHolder.get().setAttribute(WebConstants.BBS_MEMBERINFO_API_RESULT, map);
		}
	}
	*//**
	 * 
	 * @param bbsMemberInfoView
	 *//*
	public static BbsMemberInfoAPIResult getBbsMemberInfoAPIResult(String memberId){
		if(ouOnlineHolder.get()!=null){
			Map<String, BbsMemberInfoAPIResult> map = (Map<String, BbsMemberInfoAPIResult>) ouOnlineHolder.get().getAttribute(WebConstants.BBS_MEMBERINFO_API_RESULT);
			if(map != null){
				return map.get(memberId);
			}
		}
		return null;
	}
	
	public static void setMeEntityAPIResult(String memberId,MeEntityAPIResult meEntityAPIResult){
		if(ouOnlineHolder.get()!=null){
			Map<String, MeEntityAPIResult> map = (Map<String, MeEntityAPIResult>) ouOnlineHolder.get().getAttribute(WebConstants.Me_ENTITY_API_RESULT);
			if(map == null){
				map=new HashMap<String, MeEntityAPIResult> ();
			}
			map.put(memberId, meEntityAPIResult);
			ouOnlineHolder.get().setAttribute(WebConstants.Me_ENTITY_API_RESULT, map);
		}
	}
	public static MeEntityAPIResult getMeEntityAPIResult(String memberId){
		if(ouOnlineHolder.get()!=null){
			Map<String, MeEntityAPIResult> map = (Map<String, MeEntityAPIResult>) ouOnlineHolder.get().getAttribute(WebConstants.Me_ENTITY_API_RESULT);
			if(map != null){
				return map.get(memberId);
			}
		}
		return null;
	}
	
	public static void setBbsMemberStatAPIResult(String memberId,BbsMemberStatAPIResult bsMemberStatAPIResult){
		if(ouOnlineHolder.get()!=null){
			Map<String, BbsMemberStatAPIResult> map = (Map<String, BbsMemberStatAPIResult>) ouOnlineHolder.get().getAttribute(WebConstants.BBS_MEMBERSTAT_API_RESULT);
			if(map == null){
				map=new HashMap<String, BbsMemberStatAPIResult> ();
			}
			map.put(memberId, bsMemberStatAPIResult);
			ouOnlineHolder.get().setAttribute(WebConstants.BBS_MEMBERSTAT_API_RESULT, map);
		}
	}
	
	public static BbsMemberStatAPIResult getBbsMemberStatAPIResult(String memberId){
		if(ouOnlineHolder.get()!=null){
			Map<String, BbsMemberStatAPIResult> map = (Map<String, BbsMemberStatAPIResult>) ouOnlineHolder.get().getAttribute(WebConstants.BBS_MEMBERSTAT_API_RESULT);
			if(map != null){
				return map.get(memberId);
			}
		}
		return null;
	}
	public static void setMemberAPIResult(String memberId,MemberAPIResult memberAPIResult){
		if(ouOnlineHolder.get()!=null){
			Map<String, MemberAPIResult> map = (Map<String, MemberAPIResult>) ouOnlineHolder.get().getAttribute(WebConstants.MEMBER_API_RESULT);
			if(map == null){
				map=new HashMap<String, MemberAPIResult> ();
			}
			map.put(memberId, memberAPIResult);
			ouOnlineHolder.get().setAttribute(WebConstants.MEMBER_API_RESULT, map);
		}
	}
	public static MemberAPIResult getMemberAPIResult(String memberId){
		if(ouOnlineHolder.get()!=null){
			Map<String, MemberAPIResult> map = (Map<String, MemberAPIResult>) ouOnlineHolder.get().getAttribute(WebConstants.MEMBER_API_RESULT);
			if(map != null){
				return map.get(memberId);
			}
		}
		return null;
	}*/
	/*public static void setBbsRes(HashMap<String, String> bbsRes){
		if(ouOnlineHolder.get()!=null){
			ouOnlineHolder.get().setAttribute(WebConstants.BBS_RES,bbsRes);
		}
	}
	
	public static HashMap<String,	 String> getBbsRes(){
		if(ouOnlineHolder.get()!=null){
			return (HashMap<String, String>) ouOnlineHolder.get().getAttribute(WebConstants.BBS_RES);
		}
		return null;
	}*/
	
	/*public static void setRedisMemberView(String sid, MemberView memberView) throws Exception {
		if(memberView!=null){
			JedisUtil.set("RediasMember"+sid,JsonUtil.getJSONString(memberView),RedisConstants.ONLINE_ENTITY);
			JedisUtil.expire("RediasMember"+sid, 7*24*60*60);
		}else{
			JedisUtil.delByKey("RediasMember"+sid, RedisConstants.ONLINE_ENTITY);
		}
		
	}	
	public static MemberView getRedisMemberView(String sid) {
		if(StringUtils.isNotEmpty(sid)){
			if(sid.indexOf("\"")==0&& sid.lastIndexOf("\"")==sid.length()-1&& sid.length()!=1){
				sid=sid.substring(1, sid.length()-1);
			}
			String member=JedisUtil.get("RediasMember"+sid,RedisConstants.ONLINE_ENTITY);
			if(StringUtils.isNotEmpty(member)){
				MemberView memberView=(MemberView) JsonUtil.getDTO(member, MemberView.class);
				return memberView;
			}
		}
		return null;
	}*/
	
	/*public static void setRedisSid(String sid, String wxSid) throws Exception {
		if(wxSid!=null){
			JedisUtil.getInstance().set("RediasSid"+sid,wxSid,RedisConstants.ONLINE_ENTITY);
			JedisUtil.getInstance().expire("RediasSid"+sid, 7*24*60*60);
		}else{
			JedisUtil.getInstance().delByKey("RediasSid"+sid, RedisConstants.ONLINE_ENTITY);
		}
		
	}	
	public static String getRedisSid(String sid) {
		if(StringUtils.isNotEmpty(sid)){
			if(sid.indexOf("\"")==0&& sid.lastIndexOf("\"")==sid.length()-1&& sid.length()!=1){
				sid=sid.substring(1, sid.length()-1);
			}
			return JedisUtil.getInstance().get("RediasSid"+sid,RedisConstants.ONLINE_ENTITY);
		}
		return null;
	}*/

}
