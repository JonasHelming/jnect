package org.jnect.emfstore;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.jnect.bodymodel.Body;
import org.jnect.core.IBodyProvider;

public class RecordingBodyProvider implements IBodyProvider {
	Body nonRecordingBody;

	@Override
	public Body getRecordingBody() {
		return EMFStorage.getInstance().getRecordingBody();
	}

	@Override
	public void save() {
		final EMFStorage store = EMFStorage.getInstance();
		Job commitJob = new Job("Saving recorded data.") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				store.commit();
				monitor.done();
				return Status.OK_STATUS;
			}
		};
		commitJob.setUser(true); // show dialog
		commitJob.schedule();
	}

	@Override
	public Body getNonRecordingBody() {
		if (nonRecordingBody == null) {
			nonRecordingBody = EMFStorage.createAndFillBody();
		}
		return nonRecordingBody;
	}

	@Override
	public boolean canRecord() {
		return true;
	}

}
