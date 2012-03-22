package org.jnect.demo.gef;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.jnect.bodymodel.Body;

public class HumanDiagramGraphicalEditor extends GraphicalEditor {

	public HumanDiagramGraphicalEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer().setEditPartFactory(new HumanDiagramEditPartFactory());
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		//
	}

	public void setContent(Body container) {
		getGraphicalViewer().setContents(container);
	}

	@Override
	protected void initializeGraphicalViewer() {
		// TODO Auto-generated method stub
	}

}
