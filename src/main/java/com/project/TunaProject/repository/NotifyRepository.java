package com.project.TunaProject.repository;

import com.project.TunaProject.domain.Notify;

public interface NotifyRepository {

	public Notify insertNotify(Notify notify);
	
	public Integer notifyCheck(Notify notify);
}
