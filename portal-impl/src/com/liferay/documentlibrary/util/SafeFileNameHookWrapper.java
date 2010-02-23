/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.documentlibrary.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.service.ServiceContext;

import java.io.File;
import java.io.InputStream;

import java.util.Date;

/**
 * <a href="SafeFileNameHookWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class SafeFileNameHookWrapper implements Hook {

	public SafeFileNameHookWrapper(Hook hook) {
		_hook = hook;
	}

	public void addDirectory(long companyId, long repositoryId, String dirName)
		throws PortalException, SystemException {

		_hook.addDirectory(
			companyId, repositoryId, FileUtil.encodeSafeFileName(dirName));
	}

	public void addFile(
			long companyId, String portletId, long groupId, long repositoryId,
			String fileName, long fileEntryId, String properties,
			Date modifiedDate, ServiceContext serviceContext, byte[] bytes)
		throws PortalException, SystemException {

		_hook.addFile(
			companyId, portletId, groupId, repositoryId,
			FileUtil.encodeSafeFileName(fileName), fileEntryId, properties,
			modifiedDate, serviceContext, bytes);
	}

	public void addFile(
			long companyId, String portletId, long groupId, long repositoryId,
			String fileName, long fileEntryId, String properties,
			Date modifiedDate, ServiceContext serviceContext, File file)
		throws PortalException, SystemException {

		_hook.addFile(
			companyId, portletId, groupId, repositoryId,
			FileUtil.encodeSafeFileName(fileName), fileEntryId, properties,
			modifiedDate, serviceContext, file);
	}

	public void addFile(
			long companyId, String portletId, long groupId, long repositoryId,
			String fileName, long fileEntryId, String properties,
			Date modifiedDate, ServiceContext serviceContext, InputStream is)
		throws PortalException, SystemException {

		_hook.addFile(
			companyId, portletId, groupId, repositoryId,
			FileUtil.encodeSafeFileName(fileName), fileEntryId,
			properties, modifiedDate, serviceContext, is);
	}

	public void checkRoot(long companyId) throws SystemException {
		_hook.checkRoot(companyId);
	}

	public void deleteDirectory(
			long companyId, String portletId, long repositoryId, String dirName)
		throws PortalException, SystemException {

		_hook.deleteDirectory(
			companyId, portletId, repositoryId,
			FileUtil.encodeSafeFileName(dirName));
	}

	public void deleteFile(
			long companyId, String portletId, long repositoryId,
			String fileName)
		throws PortalException, SystemException {

		_hook.deleteFile(
			companyId, portletId, repositoryId,
			FileUtil.encodeSafeFileName(fileName));
	}

	public void deleteFile(
			long companyId, String portletId, long repositoryId,
			String fileName, String versionNumber)
		throws PortalException, SystemException {

		_hook.deleteFile(
			companyId, portletId, repositoryId,
			FileUtil.encodeSafeFileName(fileName), versionNumber);
	}

	public byte[] getFile(long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		return _hook.getFile(
			companyId, repositoryId, FileUtil.encodeSafeFileName(fileName));
	}

	public byte[] getFile(
			long companyId, long repositoryId, String fileName,
			String versionNumber)
		throws PortalException, SystemException {

		return _hook.getFile(
			companyId, repositoryId, FileUtil.encodeSafeFileName(fileName),
			versionNumber);
	}

	public InputStream getFileAsStream(
			long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		return _hook.getFileAsStream(
			companyId, repositoryId, FileUtil.encodeSafeFileName(fileName));
	}

	public InputStream getFileAsStream(
			long companyId, long repositoryId, String fileName,
			String versionNumber)
		throws PortalException, SystemException {

		return _hook.getFileAsStream(
			companyId, repositoryId, FileUtil.encodeSafeFileName(fileName),
			versionNumber);
	}

	public String[] getFileNames(
			long companyId, long repositoryId, String dirName)
		throws PortalException, SystemException {

		String[] fileNames = _hook.getFileNames(
			companyId, repositoryId, FileUtil.encodeSafeFileName(dirName));

		String[] decodedFileNames = new String[fileNames.length];

		for (int i = 0; i < fileNames.length; i++) {
			decodedFileNames[i] = FileUtil.decodeSafeFileName(fileNames[i]);
		}

		return decodedFileNames;
	}

	public long getFileSize(
			long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		return _hook.getFileSize(
			companyId, repositoryId, FileUtil.encodeSafeFileName(fileName));
	}

	public boolean hasFile(
			long companyId, long repositoryId, String fileName,
			String versionNumber)
		throws PortalException, SystemException {

		return _hook.hasFile(
			companyId, repositoryId, FileUtil.encodeSafeFileName(fileName),
			versionNumber);
	}

	public void move(String srcDir, String destDir) throws SystemException {
		_hook.move(srcDir, destDir);
	}

	public void reindex(String[] ids) throws SearchException {
		_hook.reindex(ids);
	}

	public void updateFile(
			long companyId, String portletId, long groupId, long repositoryId,
			long newRepositoryId, String fileName, long fileEntryId)
		throws PortalException, SystemException {

		_hook.updateFile(
			companyId, portletId, groupId, repositoryId, newRepositoryId,
			FileUtil.encodeSafeFileName(fileName), fileEntryId);
	}

	public void updateFile(
			long companyId, String portletId, long groupId, long repositoryId,
			String fileName, String versionNumber, String sourceFileName,
			long fileEntryId, String properties, Date modifiedDate,
			ServiceContext serviceContext, byte[] bytes)
		throws PortalException, SystemException {

		_hook.updateFile(
			companyId, portletId, groupId, repositoryId,
			FileUtil.encodeSafeFileName(fileName), versionNumber,
			FileUtil.encodeSafeFileName(sourceFileName), fileEntryId,
			properties, modifiedDate, serviceContext, bytes);
	}

	public void updateFile(
			long companyId, String portletId, long groupId, long repositoryId,
			String fileName, String versionNumber, String sourceFileName,
			long fileEntryId, String properties, Date modifiedDate,
			ServiceContext serviceContext, File file)
		throws PortalException, SystemException {

		_hook.updateFile(
			companyId, portletId, groupId, repositoryId,
			FileUtil.encodeSafeFileName(fileName),
			versionNumber, FileUtil.encodeSafeFileName(sourceFileName),
			fileEntryId, properties, modifiedDate, serviceContext, file);
	}

	public void updateFile(
			long companyId, String portletId, long groupId, long repositoryId,
			String fileName, String versionNumber, String sourceFileName,
			long fileEntryId, String properties, Date modifiedDate,
			ServiceContext serviceContext, InputStream is)
		throws PortalException, SystemException {

		_hook.updateFile(
			companyId, portletId, groupId, repositoryId,
			FileUtil.encodeSafeFileName(fileName), versionNumber,
			FileUtil.encodeSafeFileName(sourceFileName), fileEntryId,
			properties, modifiedDate, serviceContext, is);
	}

	public void updateFile(
			long companyId, String portletId, long groupId, long repositoryId,
			String fileName, String newFileName, boolean reindex)
		throws PortalException, SystemException {

		_hook.updateFile(
			companyId, portletId, groupId, repositoryId,
			FileUtil.encodeSafeFileName(fileName),
			FileUtil.encodeSafeFileName(newFileName), reindex);
	}

	private Hook _hook;

}