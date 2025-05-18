const itemForm = document.getElementById('itemForm');
const filterForm = document.getElementById('filterForm');
const itemTableBody = document.getElementById('itemTableBody');

itemForm.onsubmit = async (element) =>
    {
        element.preventDefault();
        const formData = new FormData(itemForm);
        const id = formData.get("id");
        const name = formData.get("name");
        const price = parseFloat(formData.get("price"));

        const item = {name, price};
        const method = id ? 'PUT' : 'POST';
        const url = id ? `/data/${id}` : '/data';

        await fetch(url,
            {
                method,
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(item)
            });

        itemForm.reset();
        await loadItems(filterForm.sortBy.value);
    };

async function loadItems(sortBy)
{
    const response = await fetch(`/data?sortBy=${sortBy}`);
    const items = await response.json();

    itemTableBody.innerHTML = '';

    items.forEach((item) =>
    {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${item.name}</td>
            <td>${item.price.toFixed(2)}</td>
            <td>
                <button class="button" onclick='editItem(${JSON.stringify(item)})'>Edytuj</button>
                <button class="button" onclick='deleteItem(${item.id})'>Usuń</button>
            </td>`;
        itemTableBody.appendChild(row);
    })
}

async function editItem(item)
{
    itemForm.id.value = item.id;
    itemForm.name.value = item.name;
    itemForm.price.value = item.price;
}

async function deleteItem(id)
{
    await fetch(`/data/${id}`, {method: 'DELETE'});
    await loadItems(filterForm.sortBy.value);
}

window.addEventListener('DOMContentLoaded', () =>
{loadItems(filterForm.sortBy.value || 'name');});