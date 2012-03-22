package org.jnect.demo.gef;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.jnect.bodymodel.Body;

public class HumanContainerEditPart extends AbstractGraphicalEditPart {
	  @Override
	  protected IFigure createFigure() {
	    FreeformLayer layer = new FreeformLayer();
	    layer.setLayoutManager(new FreeformLayout());
	    layer.setBorder(new LineBorder(1));
	    return layer;
	  }
	 
	  @Override
	  protected void createEditPolicies() {
	    // TODO Auto-generated method stub
	  }
	 
	  @Override protected EList<EObject> getModelChildren() {
//	    List<PositionedElement> retVal = new ArrayList<PositionedElement>();
	    Body opd = (Body) getModel();
	    return opd.eContents();
	  }
	}
