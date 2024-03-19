export function createTreeNode(treeNode) {
  treeNode['checked'] = true;
  treeNode['dragDisabled'] = true;
  treeNode['addTreeNodeDisabled'] = true;
  treeNode['addLeafNodeDisabled'] = true;
  treeNode['editNodeDisabled'] = true;
  treeNode['delNodeDisabled'] = true;
  if (treeNode['children']) {
    treeNode['children'].forEach(node => createTreeNode(node));
  }
}
