/**
 SpagoBI, the Open Source Business Intelligence suite

 Copyright (C) 2012 Engineering Ingegneria Informatica S.p.A. - SpagoBI Competency Center
 This Source Code Form is subject to the terms of the Mozilla Public
 License, v. 2.0. If a copy of the MPL was not distributed with this file,
 You can obtain one at http://mozilla.org/MPL/2.0/.
 
**/
package it.eng.spagobi.studio.core.wizards;

import it.eng.spagobi.studio.core.builder.SpagoBIStudioNature;
import it.eng.spagobi.studio.core.util.Utilities;
import it.eng.spagobi.studio.core.views.actionProvider.ResourceNavigatorActionProvider;
import it.eng.spagobi.studio.utils.util.SpagoBIStudioConstants;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewSpagoBIProjectWizard extends Wizard implements INewWizard, IExecutableExtension {

	private WizardNewProjectCreationPage creationPage;
	private IConfigurationElement configElement = null;

	public static String SPAGOBI_PROJECT_WIZARD_ID = "it.eng.spagobi.studio.core.wizards.newSpagoBIProjectWizard";

	private static Logger logger = LoggerFactory.getLogger(NewSpagoBIProjectWizard.class);

	
	public NewSpagoBIProjectWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	public boolean performFinish() {
		try {
			WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
				protected void execute(IProgressMonitor monitor) {
					IProject projRef = createProject(monitor != null ? monitor
							: new NullProgressMonitor());
					buildProjectStructure(monitor != null ? monitor
							: new NullProgressMonitor(), projRef);
				}
			};
			getContainer().run(false, true, op);


			
			BasicNewProjectResourceWizard.updatePerspective(configElement);

		} catch (InvocationTargetException x) {
			reportError(x);
			return false;
		} catch (InterruptedException x) {
			reportError(x);
			return false;
		}
		return true; 
	}
	
	
	/** create and open the project
	 * 
	 * @param monitor
	 * @return a reference to the project
	 */

	protected IProject createProject(IProgressMonitor monitor) {
		monitor.beginTask("Creating Project",50);
		IProject project = null;
		try {

			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			monitor.subTask("Creating Project Directories ");
			project = root.getProject(creationPage.getProjectName());
			IProjectDescription description = ResourcesPlugin.getWorkspace().newProjectDescription(project.getName());
			if(!Platform.getLocation().equals(creationPage.getLocationPath()))
				description.setLocation(creationPage.getLocationPath());

			// set the nature 
			String[] natures = new String[1];
			natures[0] = SpagoBIStudioNature.NATURE_ID;
			description.setNatureIds(natures);
			// create project 
			project.create(description,monitor);
			monitor.worked(10);
			project.open(monitor);
			monitor.worked(10);
		} catch(CoreException x) {
			reportError(x);
		} finally {
			monitor.done();
		}
		return project;
	}


	/**
	 *  Function that creates folder structure
	 * @param monitor
	 * @param projectReference
	 */

	protected void buildProjectStructure(IProgressMonitor monitor, IProject projectReference ) {
		logger.debug("IN");
		monitor.beginTask("Creating Project",50);
		try{
			monitor.subTask("Creating Project Directories ");
			Properties properties = new Utilities().getStudioMetaProperties();	
			
			// create SpagoBI project structure
			if(Utilities.readBooleanProperty(properties, SpagoBIStudioConstants.CONFIG_PROPERTY_FOLDER_RESOURCES)){
				logger.debug("created "+SpagoBIStudioConstants.FOLDER_RESOURCE);
				IFolder resourceFolder = projectReference.getFolder(SpagoBIStudioConstants.FOLDER_RESOURCE);
				resourceFolder.create(false, true, monitor);
				IFolder serverFolder = resourceFolder.getFolder(SpagoBIStudioConstants.FOLDER_SERVER);
				serverFolder.create(false, true, monitor);
			}
			else{
				logger.debug("not create "+SpagoBIStudioConstants.FOLDER_RESOURCE);
			}


//			IFolder datasourceFolder = resourceFolder.getFolder(SpagoBIStudioConstants.FOLDER_DATA_SOURCE);
//			datasourceFolder.create(false, true, monitor);
			if(Utilities.readBooleanProperty(properties, SpagoBIStudioConstants.CONFIG_PROPERTY_FOLDER_BUSINESS_MODELS)){
				logger.debug("created "+SpagoBIStudioConstants.FOLDER_METADATA_MODEL);
				IFolder metadataFolder = projectReference.getFolder(SpagoBIStudioConstants.FOLDER_METADATA_MODEL);
				metadataFolder.create(false, true, monitor);
			}
			else{
				logger.debug("not create "+SpagoBIStudioConstants.FOLDER_METADATA_MODEL);
			}

			if(Utilities.readBooleanProperty(properties, SpagoBIStudioConstants.CONFIG_PROPERTY_FOLDER_BUSINESS_QUERIES)){
				logger.debug("created "+SpagoBIStudioConstants.FOLDER_DATASET);
				IFolder datasetFolder = projectReference.getFolder(SpagoBIStudioConstants.FOLDER_DATASET);
				datasetFolder.create(false, true, monitor);
			}
			else{
				logger.debug("not create "+SpagoBIStudioConstants.FOLDER_DATASET);
			}

			if(Utilities.readBooleanProperty(properties, SpagoBIStudioConstants.CONFIG_PROPERTY_FOLDER_BUSINESS_ANALYSIS)){		
				logger.debug("created "+SpagoBIStudioConstants.FOLDER_ANALYSIS);
				IFolder analysisFolder = projectReference.getFolder(SpagoBIStudioConstants.FOLDER_ANALYSIS);
				analysisFolder.create(false, true, monitor);
			}
			else{
				logger.debug("not create "+SpagoBIStudioConstants.FOLDER_ANALYSIS);
			}
			
			if(Utilities.readBooleanProperty(properties, SpagoBIStudioConstants.CONFIG_PROPERTY_FOLDER_OLAP_TEMPLATES)){		
				logger.debug("created "+SpagoBIStudioConstants.FOLDER_OLAP_TEMPLATES);
				IFolder olapFolder = projectReference.getFolder(SpagoBIStudioConstants.FOLDER_OLAP_TEMPLATES);
				olapFolder.create(false, true, monitor);
			}
			else{
				logger.debug("not create "+SpagoBIStudioConstants.FOLDER_OLAP_TEMPLATES);
			}

			if(Utilities.readBooleanProperty(properties, SpagoBIStudioConstants.CONFIG_PROPERTY_FOLDER_PRIVATE_FOLDERS)){		
				logger.debug("created "+SpagoBIStudioConstants.FOLDER_PRIVATE_DOCUMENTS);
				IFolder privateFolder = projectReference.getFolder(SpagoBIStudioConstants.FOLDER_PRIVATE_DOCUMENTS);
				privateFolder.create(false, true, monitor);
			}
			else{
				logger.debug("not create "+SpagoBIStudioConstants.FOLDER_PRIVATE_DOCUMENTS);
			}
			
			
		} catch(CoreException x) {
			reportError(x);
		} finally {
			monitor.done();
		}
		logger.debug("OUT");

	}





	public final void init(final IWorkbench workbench, final IStructuredSelection selectionParam) {
		setNeedsProgressMonitor(true);
	}

	private void reportError(Exception x) {
		ErrorDialog.openError(getShell(), "Error", "Error in Creating New Project", makeStatus(x));
	}


	public static IStatus makeStatus(Exception x){
		return new Status(IStatus.ERROR, "", IStatus.ERROR, x.getMessage(), null);
	}


	public void setInitializationData(IConfigurationElement confEl, String arg1, Object arg2) throws CoreException {
		configElement = confEl;
	}

	public final void addPages() {
		try{
			super.addPages();
			creationPage = new WizardNewProjectCreationPage("New SpagoBI Project Page");
			creationPage.setTitle("New SpagoBI Project");
			addPage(creationPage);
		} catch(Exception x) {
			reportError(x);
		}
	}

}
