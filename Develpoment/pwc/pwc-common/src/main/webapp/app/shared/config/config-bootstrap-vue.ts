import {
  BAlert,
  BBadge,
  BButton,
  BButtonGroup,
  BButtonToolbar,
  BCol,
  BCollapse,
  BContainer,
  BDropdown,
  BDropdownItem,
  BDropdownItemButton,
  BForm,
  BFormCheckbox,
  BFormCheckboxGroup,
  BFormDatepicker,
  BFormFile,
  BFormGroup,
  BFormInput,
  BFormInvalidFeedback,
  BFormRadio,
  BFormRadioGroup,
  BFormRow,
  BFormSelect,
  BFormSelectOption,
  BFormSelectOptionGroup,
  BFormTextarea,
  BInputGroup,
  BInputGroupAppend,
  BInputGroupPrepend,
  BInputGroupText,
  BLink,
  BModal,
  BNavbar,
  BNavbarBrand,
  BNavbarNav,
  BNavbarToggle,
  BNavItem,
  BNavItemDropdown,
  BPagination,
  BPopover,
  BProgress,
  BProgressBar,
  BRow,
  BSpinner,
  BTable,
  BTableSimple,
  BTbody,
  BTd,
  BTh,
  BThead,
  BTooltip,
  BTr,
  VBModal,
  BDropdownDivider,
  BDropdownForm,
  BCardGroup,
  BCard,
  BCardHeader,
  BCardBody,
  BCardText,
  BListGroup,
  BListGroupItem,
  BTabs,
  BTab,
  BootstrapVueIcons,
  VBToggle,
  BSidebar,
  BImg,
} from 'bootstrap-vue';

export function initBootstrapVue(vue) {
  vue.component('b-alert', BAlert);
  vue.component('b-badge', BBadge);
  vue.component('b-button', BButton);
  vue.component('b-button-group', BButtonGroup);
  vue.component('b-button-toolbar', BButtonToolbar);
  vue.component('b-col', BCol);
  vue.component('b-collapse', BCollapse);
  vue.component('b-container', BContainer);
  vue.component('b-dropdown', BDropdown);
  vue.component('b-dropdown-divider', BDropdownDivider);
  vue.component('b-dropdown-form', BDropdownForm);
  vue.component('b-dropdown-item', BDropdownItem);
  vue.component('b-dropdown-item-button', BDropdownItemButton);
  vue.component('b-form', BForm);
  vue.component('b-form-checkbox', BFormCheckbox);
  vue.component('b-form-checkbox-group', BFormCheckboxGroup);
  vue.component('b-form-datepicker', BFormDatepicker);
  vue.component('b-form-file', BFormFile);
  vue.component('b-form-group', BFormGroup);
  vue.component('b-form-input', BFormInput);
  vue.component('b-form-invalid-feedback', BFormInvalidFeedback);
  vue.component('b-form-radio', BFormRadio);
  vue.component('b-form-radio-group', BFormRadioGroup);
  vue.component('b-form-row', BFormRow);
  vue.component('b-form-select', BFormSelect);
  vue.component('b-form-select-option', BFormSelectOption);
  vue.component('b-form-select-option-group', BFormSelectOptionGroup);
  vue.component('b-form-textarea', BFormTextarea);
  vue.component('b-input-group', BInputGroup);
  vue.component('b-input-group-append', BInputGroupAppend);
  vue.component('b-input-group-prepend', BInputGroupPrepend);
  vue.component('b-input-group-text', BInputGroupText);
  vue.component('b-link', BLink);
  vue.component('b-modal', BModal);
  vue.directive('b-modal', VBModal);
  vue.component('b-nav-item', BNavItem);
  vue.component('b-nav-item-dropdown', BNavItemDropdown);
  vue.component('b-navbar', BNavbar);
  vue.component('b-navbar-brand', BNavbarBrand);
  vue.component('b-navbar-nav', BNavbarNav);
  vue.component('b-navbar-toggle', BNavbarToggle);
  vue.component('b-pagination', BPagination);
  vue.component('b-popover', BPopover);
  vue.component('b-progress', BProgress);
  vue.component('b-progress-bar', BProgressBar);
  vue.component('b-row', BRow);
  vue.component('b-spinner', BSpinner);
  vue.component('b-table', BTable);
  vue.component('b-table-simple', BTableSimple);
  vue.component('b-tbody', BTbody);
  vue.component('b-td', BTd);
  vue.component('b-th', BTh);
  vue.component('b-thead', BThead);
  vue.component('b-tooltip', BTooltip);
  vue.component('b-tr', BTr);
  vue.component('b-list-group', BListGroup);
  vue.component('b-card-group', BCardGroup);
  vue.component('b-card', BCard);
  vue.component('b-card-header', BCardHeader);
  vue.component('b-card-body', BCardBody);
  vue.component('b-card-text', BCardText);
  vue.component('b-list-group', BListGroup);
  vue.component('b-tabs', BTabs);
  vue.component('b-tab', BTab);
  vue.component('b-list-group-item', BListGroupItem);
  vue.use(BootstrapVueIcons);
  vue.directive('b-toggle', VBToggle);
  vue.component('b-sidebar', BSidebar);
  vue.component('b-img', BImg);
}
