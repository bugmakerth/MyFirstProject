package th.co.toyota.fn0.service.prs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import th.co.toyota.fn0.api.model.common.ButtonControlMaster;
import th.co.toyota.fn0.api.model.common.PVHeader;
import th.co.toyota.fn0.api.model.common.ProcessCurrent;
import th.co.toyota.fn0.api.model.common.TemplateMaster;
import th.co.toyota.fn0.form.prs.CFN02310Form;
import th.co.toyota.fn0.form.prs.CFN02311Form;
import th.co.toyota.fn0.form.prs.CFN02312Form;
import th.co.toyota.fn0.repository.prs.IFN02310Repository;
import th.co.toyota.st3.api.util.CST32010DocNoGenerator;

@Service
public class CFN02310ServiceImpl implements IFN02310Service{

	@Autowired
	protected IFN02310Repository repository;
	
	@Autowired
	private CST32010DocNoGenerator docNoGen;
	
	@Override
	public List<TemplateMaster> getCommonTemplate(String companyName,
			String module) {
		// TODO Auto-generated method stub
		return repository.getCommonTemplate(companyName.toUpperCase(), module.toUpperCase());
	}
	
	@Override
	public List<TemplateMaster> getTemplateByCategory(String companyName,
			String module, String category) {
		return repository.getTemplateByCategory(companyName.toUpperCase(), module.toUpperCase(), category);
	}
	
	@Override
	public List<TemplateMaster> getAllTemplate(String companyName, String module) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ButtonControlMaster> getButtonListByOperation(String operation) {
		return repository.getButtonControlByOperation(operation);
	}

	@Override
	public ProcessCurrent getPVInformation(String companyName, String module,
			String pvNo, String revNo) {
		return repository.getPVInformation(companyName.toUpperCase(), module.toUpperCase(), pvNo, revNo);
	}
	
	public boolean saveDraft(CFN02310Form form) throws Exception {
		CFN02311Form requesterForm = form.getRequesterForm();
		PVHeader pv = this.setFormToPvHeaderModel(form);
		
		docNoGen.generateDocNo("PV", new Date());
		repository.saveDraft(pv, null, null);
		return true;
	}
	
	public PVHeader setFormToPvHeaderModel(CFN02310Form form) {
		PVHeader pv = new PVHeader();
		CFN02311Form requester = form.getRequesterForm();
		
		pv.setRequestEmpCD(requester.getRequestEmpCd());
		pv.setRequestTel(requester.getRequestPhone());
		pv.setRequestEmpTitle(requester.getRequestTitle());
		pv.setRequestEmpName(requester.getRequestFirstName());
		pv.setRequestEmpLastName(requester.getRequestLastName());
		pv.setRequestJobDesc(requester.getRequestJobDesc());
		pv.setRequestPosition(requester.getRequestPosition());
		pv.setRequestDivisionName(requester.getRequestDivisionName());
		pv.setRequestDepartmentName(requester.getRequestDepartment());
		pv.setRequestSectionName(requester.getRequestSection());		
		
		CFN02312Form user = form.getUserForm();
		pv.setUserEmpCD(user.getUserEmpCd());
		pv.setUserTel(user.getUserPhone());
		pv.setUserEmpTitle(user.getUserTitle());
		pv.setUserEmpName(user.getUserFirstName());
		pv.setUserEmpLastName(user.getUserLastName());
		pv.setUserJobDesc(user.getUserJobDesc());
		pv.setUserPosition(user.getUserPosition());
		pv.setUserDivisionName(user.getUserDivisionName());
		pv.setUserDepartmentName(user.getUserDepartment());
		pv.setUserSectionName(user.getUserSection());
		pv.setUserCostCenter(user.getUserCostCenter());
		
		return pv;
	}
	
	@Override
	public List<String> validateForm(MessageSource messageSource, Locale locale, CFN02310Form form){
		List<String> errors = new ArrayList<String>();
		
//		Validate for requester Info
		if (!Strings.isNullOrEmpty(form.getRequesterForm().getDisplayRequesterInfo())) {
//			TODO Implement for validate data	
		}
		
//		Validate for User Info
		if (!Strings.isNullOrEmpty(form.getUserForm().getDisplayUserInfo())) {
//			TODO Implement for validate data			
		}
		
//		Validate for Approval Info
		if (!Strings.isNullOrEmpty(form.getApprovalForm().getDisplayApprovalInfo())) {
//			TODO Implement for validate data			
		}

		
		return errors;
	}

}
